package com.br.marcus.saborfy.domain.payment.service;

public interface DeletePaymentService {
    void deletePayment(Long orderId, Long paymentId);
}
