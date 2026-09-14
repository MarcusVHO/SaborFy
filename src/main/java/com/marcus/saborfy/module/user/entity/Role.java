package com.marcus.saborfy.module.user.entity;

import com.marcus.saborfy.module.user.enuns.RoleName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table(name = "role")
@Getter
public class Role implements GrantedAuthority {
    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @Override
    public @Nullable String getAuthority() {
        return "ROLE_" + name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<User> users;

}
