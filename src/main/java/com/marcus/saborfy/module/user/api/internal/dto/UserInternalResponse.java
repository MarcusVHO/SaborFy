package com.marcus.saborfy.module.user.api.internal.dto;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.util.List;

public record UserInternalResponse (
        Long id,
        Long restaurantId,
        String username,
        SimpleGrantedAuthority role,
        boolean active,
        Instant createdAt,
        Instant updatedAt
) {
}
