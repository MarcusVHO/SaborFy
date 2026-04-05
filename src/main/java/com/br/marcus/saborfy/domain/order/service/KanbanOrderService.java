package com.br.marcus.saborfy.domain.order.service;

import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.enums.OrderStatus;

import java.time.Instant;
import java.util.List;

public interface KanbanOrderService {

    List<Order> getOrdersForKanban(OrderStatus status, Instant startDate, Instant endDate);
}
