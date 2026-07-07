package com.br.marcus.saborfy.domain.payment.service.impl;

import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.service.OrderService;
import com.br.marcus.saborfy.domain.payment.dto.request.CreatePaymentRequest;
import com.br.marcus.saborfy.domain.payment.dto.request.UpdatePaymentRequest;
import com.br.marcus.saborfy.domain.payment.dto.request.UpdatePaymentStatusRequest;
import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.domain.payment.enums.PaymentStatus;
import com.br.marcus.saborfy.domain.payment.repository.PaymentRepository;
import com.br.marcus.saborfy.domain.payment.service.PaymentService;
import com.br.marcus.saborfy.exceptions.OrderNotFoundException;
import com.br.marcus.saborfy.exceptions.PaymentNotCanDelete;
import com.br.marcus.saborfy.exceptions.PaymentNotFoundException;
import com.br.marcus.saborfy.exceptions.PaymentStatusInvalid;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderService orderService) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
    }

    public Payment createPayment(AuthenticatedUser user, CreatePaymentRequest request, Long orderId) {
        Order order = orderService.getOrder(orderId);

        Payment newPayment = new Payment();
        newPayment.setOrder(order);
        newPayment.setMethod(request.method());
        newPayment.setAmount(request.amount());
        newPayment.setStatus(PaymentStatus.PENDING);

        paymentRepository.save(newPayment);
        return newPayment;
    }

    @Override
    public void deletePayment(Long orderId, Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(PaymentNotFoundException::new);

        if(!payment.getOrder().getId().equals(orderId)) {
            throw new OrderNotFoundException();
        }

        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new PaymentNotCanDelete();
        }

        paymentRepository.delete(payment);

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

    @Override
    public Payment updateStatus(UpdatePaymentStatusRequest request, Long orderId, Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(PaymentNotFoundException::new);
        if (!payment.getOrder().getId().equals(orderId)) {
            throw new OrderNotFoundException();
        }


        if (payment.getStatus() != PaymentStatus.CANCELED && request.status() == PaymentStatus.CANCELED) {
            payment.setStatus(request.status());
            paymentRepository.save(payment);
            return payment;
        }

        if (payment.getStatus() == PaymentStatus.PENDING && request.status() == PaymentStatus.APPROVED) {
            payment.setPaidAt(Instant.now());
            payment.setStatus(request.status());
            paymentRepository.save(payment);
            return payment;
        }

        if (payment.getStatus() == PaymentStatus.APPROVED && request.status() == PaymentStatus.REFUNDED) {
            payment.setStatus(request.status());
            paymentRepository.save(payment);
            return payment;
        }

        throw new PaymentStatusInvalid();
    }
}
