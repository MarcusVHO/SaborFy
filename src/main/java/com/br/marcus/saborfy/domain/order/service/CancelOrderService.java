package com.br.marcus.saborfy.domain.order.service;

import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;

public interface CancelOrderService {
    void cancelOrder(AuthenticatedUser user, Long id);
}
