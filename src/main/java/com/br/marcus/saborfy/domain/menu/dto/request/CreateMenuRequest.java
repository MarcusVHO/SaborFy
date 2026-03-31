package com.br.marcus.saborfy.domain.menu.dto.request;

import jakarta.validation.constraints.NotEmpty;


public record CreateMenuRequest(
        @NotEmpty(message = "Name is necessary!") String name
) {
}
