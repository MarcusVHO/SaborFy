package com.br.marcus.saborfy.domain.order.dto.response;

import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.entity.OrderItem;
import com.br.marcus.saborfy.domain.order.enums.OrderStatus;
import com.br.marcus.saborfy.domain.payment.dto.response.PaymentResponse;
import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.domain.payment.enums.PaymentStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderResponse {
    private final Long id;
    private final String orderNumber;
    private final OrderStatus status;
    private final PaymentStatus paymentStatus;
    private String observation;
    private final OrderCustomerResponse customer;
    private final OrderAddressResponse address;
    private final List<OrderItemResponse> items = new ArrayList<>();
    private final List<PaymentResponse> payments = new ArrayList<>();
    private final BigDecimal total_amount;

    public OrderResponse (Order order) {
        this.id = order.getId();
        this.orderNumber = order.getOrderNumber();
        this.status = order.getOrderStatus();
        this.paymentStatus = order.getPaymentStatus();
        this.customer = new OrderCustomerResponse(order.getCustomer());
        this.address = new OrderAddressResponse(order.getAddress());
        this.total_amount = order.getTotalAmount();
        if (order.getObservation() != null) {
            this.observation = order.getObservation().getObservation();
        }

        for (OrderItem item : order.getOrderItems()) {
            OrderItemResponse itemResponse = new OrderItemResponse(item);
            this.items.add(itemResponse);
        }

        for (Payment payment : order.getPayments()) {
            PaymentResponse paymentResponse = new PaymentResponse(payment);
            this.payments.add(paymentResponse);
        }
    }
}
