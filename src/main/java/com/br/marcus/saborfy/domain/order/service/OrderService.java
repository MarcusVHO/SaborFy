package com.br.marcus.saborfy.domain.order.service;

import com.br.marcus.saborfy.domain.order.dto.request.OrderFilterRequest;
import com.br.marcus.saborfy.domain.order.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findOrders(OrderFilterRequest request);
    Order getOrder(Long orderId);
}
