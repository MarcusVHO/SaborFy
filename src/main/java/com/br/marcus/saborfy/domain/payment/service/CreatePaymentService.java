package com.br.marcus.saborfy.domain.payment.service;

import com.br.marcus.saborfy.domain.payment.dto.request.CreatePaymentRequest;
import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;

public interface CreatePaymentService {
    Payment createPayment(AuthenticatedUser user, CreatePaymentRequest request, Long orderId);
}
