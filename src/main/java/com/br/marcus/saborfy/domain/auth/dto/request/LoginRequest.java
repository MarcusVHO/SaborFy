package com.br.marcus.saborfy.domain.auth.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull(message = "Registration é obrigatorio") Long registration,
        @NotEmpty(message = "Senha é obrigatoria") String password
    ) {

}
