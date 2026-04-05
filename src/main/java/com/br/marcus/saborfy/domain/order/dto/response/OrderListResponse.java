package com.br.marcus.saborfy.domain.order.dto.response;

import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.entity.OrderItem;
import com.br.marcus.saborfy.domain.order.enums.OrderStatus;
import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.domain.payment.enums.PaymentMethod;
import com.br.marcus.saborfy.domain.payment.enums.PaymentStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderListResponse {
    private final Long id;
    private final String orderNumber;
    private final OrderStatus status;
    private final PaymentStatus paymentStatus;
    private String observation;
    private final OrderCustomerResponse customer;
    private final OrderAddressResponse address;
    private final List<OrderItemListResponse> items = new ArrayList<>();
    private final List<PaymentMethod> methods = new ArrayList<>();
    private final BigDecimal total_amount;
    private final Instant createdAt;

    public OrderListResponse (Order order) {
        this.id = order.getId();
        this.orderNumber = order.getOrderNumber();
        this.status = order.getOrderStatus();
        this.paymentStatus = order.getPaymentStatus();
        this.customer = new OrderCustomerResponse(order.getCustomer());
        this.address = new OrderAddressResponse(order.getAddress());
        this.total_amount = order.getTotalAmount();
        this.createdAt = order.getCreatedAt();

        if (order.getObservation() != null) {
            this.observation = order.getObservation().getObservation();
        }


        for (OrderItem item : order.getOrderItems()) {
            OrderItemListResponse itemResponse = new OrderItemListResponse(item);
            this.items.add(itemResponse);
        }

        for (Payment payment : order.getPayments()) {
            this.methods.add(payment.getMethod());
        }
    }
}
