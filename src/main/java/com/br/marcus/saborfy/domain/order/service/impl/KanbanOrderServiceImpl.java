package com.br.marcus.saborfy.domain.order.service.impl;

import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.enums.OrderStatus;
import com.br.marcus.saborfy.domain.order.repository.OrderRepository;
import com.br.marcus.saborfy.domain.order.service.KanbanOrderService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class KanbanOrderServiceImpl implements KanbanOrderService {
    private final OrderRepository orderRepository;

    public KanbanOrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrdersForKanban(OrderStatus status, Instant startDate, Instant endDate) {
        return orderRepository.findByOrderStatusAndCreatedAtBetween(status, startDate, endDate);
    }
}
