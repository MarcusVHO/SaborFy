package com.br.marcus.saborfy.domain.order.service.impl;

import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.enums.OrderStatus;
import com.br.marcus.saborfy.domain.order.repository.OrderRepository;
import com.br.marcus.saborfy.domain.order.service.CancelOrderService;
import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.domain.payment.enums.PaymentStatus;
import com.br.marcus.saborfy.exceptions.OrderNotFoundException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;

@Service
public class CancelOrderServiceImpl implements CancelOrderService {
    private final OrderRepository orderRepository;

    public CancelOrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void cancelOrder(AuthenticatedUser user, Long id) {
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        order.setOrderStatus(OrderStatus.CANCELED);
        for (Payment payment : order.getPayments()) {
            if(payment.getStatus() != PaymentStatus.APPROVED && payment.getStatus() != PaymentStatus.REFUNDED) {
                payment.setStatus(PaymentStatus.CANCELED);
                }
        }
        order.setLatestUpdateBy(user.id());
        orderRepository.save(order);
    }
}
