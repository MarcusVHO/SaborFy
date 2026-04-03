package com.br.marcus.saborfy.domain.order.service.impl;

import com.br.marcus.saborfy.domain.order.dto.request.OrderFilterRequest;
import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.repository.OrderRepository;
import com.br.marcus.saborfy.domain.order.service.OrderService;
import com.br.marcus.saborfy.exceptions.OrderNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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


}
