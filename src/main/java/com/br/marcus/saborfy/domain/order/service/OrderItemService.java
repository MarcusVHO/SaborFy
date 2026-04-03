package com.br.marcus.saborfy.domain.order.service;

import com.br.marcus.saborfy.domain.order.dto.request.CreateOrderItemRequest;
import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.entity.OrderItem;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.jspecify.annotations.NonNull;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> create(@NonNull List<CreateOrderItemRequest> items, Order order, AuthenticatedUser user);
}
