package com.br.marcus.saborfy.domain.order.service.impl;

import com.br.marcus.saborfy.domain.order.dto.request.CreateOrderItemRequest;
import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.entity.OrderItem;
import com.br.marcus.saborfy.domain.order.repository.OrderRepository;
import com.br.marcus.saborfy.domain.order.service.CreateOrderItemService;
import com.br.marcus.saborfy.domain.order.service.OrderItemService;
import com.br.marcus.saborfy.exceptions.OrderNotFoundException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateOrderItemServiceImpl implements CreateOrderItemService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;

    public CreateOrderItemServiceImpl(OrderRepository orderRepository, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
    }

    @Override
    public Order createItem(AuthenticatedUser user, Long orderId, List<CreateOrderItemRequest> itemRequests) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        List<OrderItem> orderItems = orderItemService.create(itemRequests, order, user);
        order.getOrderItems().addAll(orderItems);
        orderRepository.save(order);
        return order;
    }
}
