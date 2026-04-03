package com.br.marcus.saborfy.domain.order.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest(
        @NotNull(message = "Customer id is necessary!") Long customerId,
        @NotNull(message = "Address is necessary!") Long addressId,
        String observation,
        @NotEmpty(message = "Item is necessary for create a new order!") List<CreateOrderItemRequest> items
) {
}
