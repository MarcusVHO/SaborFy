package com.br.marcus.saborfy.domain.order.dto.response;

import com.br.marcus.saborfy.domain.order.entity.OrderItem;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class OrderItemResponse {
    private final Long id;
    private final String productName;
    private final Integer quantity;
    private final BigDecimal unitPrice;
    private final BigDecimal subTotal;

    public OrderItemResponse(OrderItem item) {
        this.id = item.getId();
        this.productName = item.getProductName();
        this.quantity = item.getQuantity();
        this.unitPrice = item.getUnitPrice();
        this.subTotal = item.getTotalPrice();
    }
}

