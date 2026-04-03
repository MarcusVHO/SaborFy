package com.br.marcus.saborfy.domain.payment.service;

import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.domain.payment.enums.PaymentStatus;

public interface UpdatePaymentStatusService{
    Payment updateStatus(PaymentStatus status, Long orderId, Long paymentId);
}
