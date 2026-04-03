package com.br.marcus.saborfy.domain.order.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateOrderItemRequest(
        @NotNull(message ="Id is necessary!") Long id,
        @NotNull(message = "Quantity is necessary!") Integer quantity
) {
}
