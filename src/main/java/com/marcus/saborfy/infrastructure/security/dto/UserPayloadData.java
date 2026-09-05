package com.marcus.saborfy.infrastructure.security.dto;

import lombok.Builder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Builder
public record UserPayloadData(
        Long id,
        Long companyId,
        List<SimpleGrantedAuthority> role
) {
}
