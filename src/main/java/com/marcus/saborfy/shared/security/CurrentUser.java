package com.marcus.saborfy.shared.security;

import com.marcus.saborfy.module.user.enuns.RoleName;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public record CurrentUser(
        Long id,
        Long companyId,
        SimpleGrantedAuthority role
) {

    public RoleName getHighestRole() {
        return RoleName.valueOf(
                role.getAuthority().replace("ROLE_", "")
        );
    }
}