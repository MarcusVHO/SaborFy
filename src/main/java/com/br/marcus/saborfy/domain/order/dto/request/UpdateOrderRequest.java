package com.br.marcus.saborfy.domain.order.dto.request;

public record UpdateOrderRequest (
        Long addressId,
        String observation
){
}
