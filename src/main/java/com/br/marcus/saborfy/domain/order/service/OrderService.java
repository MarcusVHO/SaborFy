package com.br.marcus.saborfy.domain.order.service;

import com.br.marcus.saborfy.domain.order.dto.request.CreateOrderRequest;
import com.br.marcus.saborfy.domain.order.dto.request.OrderFilterRequest;
import com.br.marcus.saborfy.domain.order.dto.request.UpdateOrderRequest;
import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface OrderService {
    List<Order> findOrders(OrderFilterRequest request);
    Order getOrder(Long orderId);
    void cancelOrder(AuthenticatedUser user, Long id);
    Order createOrder(@AuthenticationPrincipal AuthenticatedUser user, CreateOrderRequest request);
    Order updateOrderAddress(AuthenticatedUser user, UpdateOrderRequest request, Long orderId);

}
