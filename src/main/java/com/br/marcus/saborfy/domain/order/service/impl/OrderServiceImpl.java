package com.br.marcus.saborfy.domain.order.service.impl;

import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.entity.CustomerAddress;
import com.br.marcus.saborfy.domain.customer.repository.AddressRepository;
import com.br.marcus.saborfy.domain.customer.repository.CustomerRepository;
import com.br.marcus.saborfy.domain.order.dto.request.CreateOrderRequest;
import com.br.marcus.saborfy.domain.order.dto.request.OrderFilterRequest;
import com.br.marcus.saborfy.domain.order.dto.request.UpdateOrderRequest;
import com.br.marcus.saborfy.domain.order.entity.*;
import com.br.marcus.saborfy.domain.order.enums.OrderStatus;
import com.br.marcus.saborfy.domain.order.repository.OrderRepository;
import com.br.marcus.saborfy.domain.order.service.OrderItemService;
import com.br.marcus.saborfy.domain.order.service.OrderService;
import com.br.marcus.saborfy.exceptions.AddressNotFoundException;
import com.br.marcus.saborfy.exceptions.CustomerNotFoundException;
import com.br.marcus.saborfy.exceptions.OrderNotFoundException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemService orderItemService;
    private final AddressRepository addressRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, OrderItemService orderItemService, AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderItemService = orderItemService;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Order> findOrders(OrderFilterRequest request) {

        return orderRepository.findOrders(
                request.name(),
                request.status(),
                request.orderNumber(),
                request.street(),
                request.addressNumber(),
                request.startDate(),
                request.endDate()
        );
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public void cancelOrder(AuthenticatedUser user, Long id) {
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        order.setOrderStatus(OrderStatus.CANCELED);
        order.setLatestUpdateBy(user.id());
        orderRepository.save(order);
    }


    @Override
    public Order createOrder(AuthenticatedUser user, CreateOrderRequest request) {
        Order newOrder = new Order();

        Customer customer = customerRepository.findById(request.customerId()).orElseThrow(() -> new CustomerNotFoundException("Customer not found for create order"));
        OrderCustomer newCustomer = new OrderCustomer();
        newCustomer.setCustomerId(customer.getId());
        newCustomer.setCustomerName(customer.getName());

        OrderAddress address = new OrderAddress();
        if(request.addressId() != null) {
            CustomerAddress customerAddress = customer.getAddresses().stream()
                    .filter(p -> Objects.equals(p.getId(), request.addressId()))
                    .findFirst()
                    .orElseThrow(AddressNotFoundException::new);
            address.setStreet(customerAddress.getAddress());
            address.setComplement(customerAddress.getComplement());
            address.setNumber(customerAddress.getNumber());
        } else {
            address.setNumber(0);
            address.setStreet("No address");
        }
        newOrder.setAddress(address);

        if (request.observation() != null) {
            OrderObservation observation = new OrderObservation();
            observation.setObservation(request.observation());
            observation.setOrder(newOrder);
            newOrder.setObservation(observation);
        }

        List<OrderItem> orderItems = orderItemService.create(request.items(), newOrder, user);

        newOrder.setOrderItems(orderItems);
        newOrder.setCustomer(newCustomer);
        newOrder.setUserId(user.id());
        newOrder.setLatestUpdateBy(user.id());
        orderRepository.save(newOrder);
        return newOrder;

    }

    @Override
    public Order updateOrderAddress(AuthenticatedUser user, UpdateOrderRequest request, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.setLatestUpdateBy(user.id());

        if (request.addressId() != null) {
            CustomerAddress address = addressRepository.findById(request.addressId()).orElseThrow(AddressNotFoundException::new);
            OrderAddress newAddress = new OrderAddress();
            newAddress.setStreet(address.getAddress());
            newAddress.setComplement(address.getComplement());
            newAddress.setNumber(address.getNumber());
            order.setAddress(newAddress);
        }

        if (request.observation() != null) {
            if (order.getObservation() != null) {
                order.getObservation().setObservation(request.observation());
            } else {
                OrderObservation newObservation = new OrderObservation();
                newObservation.setObservation(request.observation());
                newObservation.setOrder(order);
                order.setObservation(newObservation);
            }
        }

        if (request.status() != null) {
            order.setOrderStatus(request.status());
        }

        return orderRepository.save(order);
    }

}
