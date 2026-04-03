package com.br.marcus.saborfy.domain.order.service;

import com.br.marcus.saborfy.domain.order.dto.request.CreateOrderRequest;
import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface CreateOrderService {
    Order createOrder(@AuthenticationPrincipal AuthenticatedUser user, CreateOrderRequest request);
}
