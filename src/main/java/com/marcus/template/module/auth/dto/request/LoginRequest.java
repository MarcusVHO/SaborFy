package com.marcus.template.module.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Username is necessary!")
        String username,

        @NotBlank(message = "Password is necessary!")
        String password
) {
}
