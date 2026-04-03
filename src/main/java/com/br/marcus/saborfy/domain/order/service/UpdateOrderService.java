package com.br.marcus.saborfy.domain.order.service;

import com.br.marcus.saborfy.domain.order.dto.request.UpdateOrderRequest;
import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;

public interface UpdateOrderService {
    Order updateOrderAddress(AuthenticatedUser user, UpdateOrderRequest request, Long orderId);
}
