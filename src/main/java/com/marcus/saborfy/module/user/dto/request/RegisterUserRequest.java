package com.marcus.saborfy.module.user.dto.request;

import com.marcus.saborfy.module.user.enuns.RoleName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotBlank(message = "Registration is necessary") String registration,
        @NotBlank(message = "Name of user necessary") String name,
        @Size(
                min = 8,
                max = 72,
                message = "The password to have between 8 and 72 characters"
        )
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).+$",
                message = "The password must contain uppercase letters, lowercase letters, numbers, and special characters."
        )
        @NotBlank(message = "Password is necessary") String password,

        RoleName role
        ) {
}
