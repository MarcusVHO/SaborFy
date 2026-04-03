package com.br.marcus.saborfy.domain.payment.service.impl;

import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.service.OrderService;
import com.br.marcus.saborfy.domain.payment.dto.request.CreatePaymentRequest;
import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.domain.payment.enums.PaymentStatus;
import com.br.marcus.saborfy.domain.payment.repository.PaymentRepository;
import com.br.marcus.saborfy.domain.payment.service.CreatePaymentService;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;


@Service
public class CreatePaymentServiceImpl implements CreatePaymentService {
    private final OrderService orderService;
    private final PaymentRepository paymentRepository;

    public CreatePaymentServiceImpl(OrderService orderService, PaymentRepository paymentRepository) {
        this.orderService = orderService;
        this.paymentRepository = paymentRepository;
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

}
