package com.marcus.template.shared.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public record CurrentUser(
        Long id,
        Long companyId,
        List<SimpleGrantedAuthority> roles
){
}
