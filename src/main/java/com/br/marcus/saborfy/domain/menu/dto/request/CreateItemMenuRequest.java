package com.br.marcus.saborfy.domain.menu.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateItemMenuRequest(
        @NotEmpty(message = "Name is necessary!") String name,
        Double price,
        String description
) {
}
