package com.br.marcus.saborfy.domain.payment.service;

import com.br.marcus.saborfy.domain.payment.dto.request.CreatePaymentRequest;
import com.br.marcus.saborfy.domain.payment.dto.request.UpdatePaymentRequest;
import com.br.marcus.saborfy.domain.payment.dto.request.UpdatePaymentStatusRequest;
import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;

public interface PaymentService {
    Payment createPayment(AuthenticatedUser user, CreatePaymentRequest request, Long orderId);
    void deletePayment(Long orderId, Long paymentId);
    Payment updatePayment(UpdatePaymentRequest request, Long orderId, Long paymentId);
    Payment updateStatus(UpdatePaymentStatusRequest request, Long orderId, Long paymentId);

}
