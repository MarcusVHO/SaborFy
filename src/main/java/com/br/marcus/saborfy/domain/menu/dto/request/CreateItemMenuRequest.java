package com.br.marcus.saborfy.domain.menu.dto.request;

import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record CreateItemMenuRequest(
        @NotEmpty(message = "Name is necessary!") String name,
        BigDecimal price,
        String description
) {
}
