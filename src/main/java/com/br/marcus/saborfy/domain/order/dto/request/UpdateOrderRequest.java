package com.br.marcus.saborfy.domain.order.dto.request;

import com.br.marcus.saborfy.domain.order.enums.OrderStatus;

public record UpdateOrderRequest (
        Long addressId,
        String observation,
        OrderStatus status
){
}
