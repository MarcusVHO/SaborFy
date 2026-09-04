package com.marcus.template.module.user.api.internal.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.util.List;

public record AuthenticationUserDataResponse(
        Long id,
        Long companyId,
        String username,
        String passwordHash,
        List<SimpleGrantedAuthority> role,
        boolean active,
        Instant createdAt,
        Instant updatedAt
) {
}
