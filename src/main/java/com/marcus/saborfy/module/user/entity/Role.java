package com.marcus.saborfy.module.user.entity;

import com.marcus.saborfy.module.user.enuns.RoleName;
import jakarta.persistence.*;
import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role")
@Getter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

    @Override
    public @Nullable String getAuthority() {
        return "ROLE_" + name;
    }
}
