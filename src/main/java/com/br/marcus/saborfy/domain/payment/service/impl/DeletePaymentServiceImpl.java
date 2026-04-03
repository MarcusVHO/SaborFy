package com.br.marcus.saborfy.domain.payment.service.impl;

import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.domain.payment.enums.PaymentStatus;
import com.br.marcus.saborfy.domain.payment.repository.PaymentRepository;
import com.br.marcus.saborfy.domain.payment.service.DeletePaymentService;
import com.br.marcus.saborfy.exceptions.OrderNotFoundException;
import com.br.marcus.saborfy.exceptions.PaymentNotCanDelete;
import com.br.marcus.saborfy.exceptions.PaymentNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeletePaymentServiceImpl implements DeletePaymentService {
    private final PaymentRepository paymentRepository;

    public DeletePaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void deletePayment(Long orderId, Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(PaymentNotFoundException::new);

        if(!payment.getOrder().getId().equals(orderId)) {
            throw new OrderNotFoundException();
        }

        if (payment.getStatus().equals(PaymentStatus.REFUNDED) || payment.getStatus().equals(PaymentStatus.APPROVED)) {
            throw new PaymentNotCanDelete();
        }

        paymentRepository.delete(payment);
    }
}
