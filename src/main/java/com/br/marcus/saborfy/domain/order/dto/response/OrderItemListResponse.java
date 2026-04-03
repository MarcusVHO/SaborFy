package com.br.marcus.saborfy.domain.order.dto.response;

import com.br.marcus.saborfy.domain.order.entity.OrderItem;
import lombok.Getter;

@Getter
public class OrderItemListResponse {
    private final String productName;
    private final Integer quantity;

    public OrderItemListResponse(OrderItem item) {
        this.productName = item.getProductName();
        this.quantity = item.getQuantity();
    }
}
