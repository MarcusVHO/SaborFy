package com.br.marcus.saborfy.domain.payment.service.impl;

import com.br.marcus.saborfy.domain.payment.dto.request.UpdatePaymentRequest;
import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.domain.payment.repository.PaymentRepository;
import com.br.marcus.saborfy.domain.payment.service.UpdatePaymentService;
import com.br.marcus.saborfy.exceptions.OrderNotFoundException;
import com.br.marcus.saborfy.exceptions.PaymentNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UpdatePaymentServiceImpl implements UpdatePaymentService {
    private final PaymentRepository paymentRepository;

    public UpdatePaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment updatePayment(UpdatePaymentRequest request, Long orderId, Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(PaymentNotFoundException::new);
        if (!payment.getOrder().getId().equals(orderId)) {
            throw new OrderNotFoundException();
        }

        if (request.amount() != null) {
            payment.setAmount(request.amount());
        }

        if (request.method() != null) {
            payment.setMethod(request.method());
        }

        paymentRepository.save(payment);
        return payment;
    }
}
