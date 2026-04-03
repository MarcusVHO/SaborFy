package com.br.marcus.saborfy.domain.order.service.impl;

import com.br.marcus.saborfy.domain.customer.entity.CustomerAddress;
import com.br.marcus.saborfy.domain.customer.repository.CustomerAddressRepository;
import com.br.marcus.saborfy.domain.order.dto.request.UpdateOrderRequest;
import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.entity.OrderAddress;
import com.br.marcus.saborfy.domain.order.entity.OrderObservation;
import com.br.marcus.saborfy.domain.order.repository.OrderRepository;
import com.br.marcus.saborfy.domain.order.service.UpdateOrderService;
import com.br.marcus.saborfy.exceptions.AddressNotFoundException;
import com.br.marcus.saborfy.exceptions.OrderNotFoundException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrderServiceImpl implements UpdateOrderService {
    private final CustomerAddressRepository customerAddressRepository;
    private final OrderRepository orderRepository;

    public UpdateOrderServiceImpl(CustomerAddressRepository customerAddressRepository, OrderRepository orderRepository) {
        this.customerAddressRepository = customerAddressRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order updateOrderAddress(AuthenticatedUser user, UpdateOrderRequest request, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.setLatestUpdateBy(user.id());

        if (request.addressId() != null) {
            CustomerAddress address = customerAddressRepository.findById(request.addressId()).orElseThrow(AddressNotFoundException::new);
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

        return orderRepository.save(order);
    }
}
