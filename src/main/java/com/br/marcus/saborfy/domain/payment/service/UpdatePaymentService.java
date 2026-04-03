package com.br.marcus.saborfy.domain.payment.service;

import com.br.marcus.saborfy.domain.payment.dto.request.UpdatePaymentRequest;
import com.br.marcus.saborfy.domain.payment.entity.Payment;

public interface UpdatePaymentService {
    Payment updatePayment(UpdatePaymentRequest request, Long orderId, Long paymentId);
}
