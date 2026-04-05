package com.br.marcus.saborfy.domain.payment.service.impl;

import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.domain.payment.enums.PaymentStatus;
import com.br.marcus.saborfy.domain.payment.repository.PaymentRepository;
import com.br.marcus.saborfy.domain.payment.service.UpdatePaymentStatusService;
import com.br.marcus.saborfy.exceptions.OrderNotFoundException;
import com.br.marcus.saborfy.exceptions.PaymentNotFoundException;
import com.br.marcus.saborfy.exceptions.PaymentStatusInvalid;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
public class UpdatePaymentStatusServiceImpl implements UpdatePaymentStatusService {

    private final PaymentRepository paymentRepository;

    public UpdatePaymentStatusServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment updateStatus(PaymentStatus status, Long orderId, Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(PaymentNotFoundException::new);
        if (!payment.getOrder().getId().equals(orderId)) {
            throw new OrderNotFoundException();
        }

        if (payment.getStatus() == PaymentStatus.PENDING && (status == PaymentStatus.APPROVED || status == PaymentStatus.CANCELED)) {
            if(status == PaymentStatus.APPROVED) {
                payment.setPaidAt(Instant.now());
            }
            payment.setStatus(status);
            paymentRepository.save(payment);
            return payment;
        }

        if (payment.getStatus() == PaymentStatus.APPROVED && status == PaymentStatus.REFUNDED) {
            payment.setStatus(status);
            paymentRepository.save(payment);
            return payment;
        }

        throw new PaymentStatusInvalid();
    }
}
