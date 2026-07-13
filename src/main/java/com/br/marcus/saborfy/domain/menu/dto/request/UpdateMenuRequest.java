package com.br.marcus.saborfy.domain.menu.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateMenuRequest(
        @NotBlank(message = "Name is necessary for update menu!") String  name
        ) {
}
