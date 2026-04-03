package com.br.marcus.saborfy.domain.order.service;

import com.br.marcus.saborfy.domain.order.dto.request.CreateOrderItemRequest;
import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;

import java.util.List;

public interface CreateOrderItemService {
    Order createItem(AuthenticatedUser user, Long orderId, List<CreateOrderItemRequest> itemRequests);
}
