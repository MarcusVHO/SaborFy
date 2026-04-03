package com.br.marcus.saborfy.domain.order.service.impl;

import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.entity.CustomerAddress;
import com.br.marcus.saborfy.domain.customer.repository.CustomerRepository;

import com.br.marcus.saborfy.domain.order.dto.request.CreateOrderRequest;
import com.br.marcus.saborfy.domain.order.entity.*;
import com.br.marcus.saborfy.domain.order.repository.OrderRepository;
import com.br.marcus.saborfy.domain.order.service.OrderItemService;
import com.br.marcus.saborfy.domain.order.service.CreateOrderService;
import com.br.marcus.saborfy.exceptions.AddressNotFoundException;
import com.br.marcus.saborfy.exceptions.CustomerNotFoundException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CreateOrderServiceImpl implements CreateOrderService {
    private final CustomerRepository customerRepository;
    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;

    public CreateOrderServiceImpl(CustomerRepository customerRepository, OrderItemService orderItemService, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderItemService = orderItemService;
        this.orderRepository = orderRepository;
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
}
