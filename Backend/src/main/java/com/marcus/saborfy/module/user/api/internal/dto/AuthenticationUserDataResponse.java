package com.marcus.saborfy.module.user.api.internal.dto;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.util.List;

public record AuthenticationUserDataResponse(
        Long id,
        Long restaurantId,
        String username,
        String passwordHash,
        SimpleGrantedAuthority role,
        boolean active,
        Instant createdAt,
        Instant updatedAt
) {
}

