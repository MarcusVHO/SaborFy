package com.br.marcus.saborfy.domain.user.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterUserRequest (
        @NotEmpty(message = "name is necessary!") String name,
        @NotNull(message = "registration is necessary!") Long registration,
        @NotEmpty(message = "password is necessary!") String password,
        @NotEmpty(message = "role is necessary!") String role
    ){
}
