package com.marcus.template.module.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_role",
    joinColumns = @JoinColumn(name =  "users_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> role = new ArrayList<>();

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false, name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(nullable = false, name = "updated_at")
    private Instant updatedAt;

    @Override
    public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }


    public static User create(Long companyId, String username, String passwordHash, Role role) {
        User newUser = new User();
        newUser.setCompanyId(companyId);
        newUser.setUsername(username);
        newUser.setPasswordHash(passwordHash);
        newUser.role.add(role);
        newUser.setUpdatedAt(Instant.now());
        return newUser;
    }

    public void addRole(Role role) {
        this.role.add(role);
    }
}
