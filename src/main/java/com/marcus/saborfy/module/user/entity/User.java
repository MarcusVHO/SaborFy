package com.marcus.saborfy.module.user.entity;

import com.marcus.saborfy.module.user.enuns.RoleName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private Long restaurantId;

    @Setter
    @Column(nullable = false)
    private String registration;

    @Setter
    @Column(nullable = false)
    private String passwordHash;

    @Setter
    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false, name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Setter
    @Column(nullable = false, name = "updated_at")
    private Instant updatedAt;


    @Override
    public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public @Nullable String getPassword() {
        return getPasswordHash();
    }

    @Override
    @NullMarked
    public String getUsername() {
        return registration;
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


    public static User create(Long restaurantId, String registration, String passwordHash, String name, Role role) {
        return new User(
                restaurantId,
                registration,
                passwordHash,
                name,
                role
        );
    }

    public User() {

    }

    public User(Long restaurantId, String registration, String passwordHash, String name, Role role) {
        this.restaurantId = restaurantId;
        this.registration = registration;
        this.passwordHash = passwordHash;
        this.name = name;
        this.addRole(role);
        this.updatedAt = Instant.now();
    }

    public void addRole(Role role) {
        this.role = role;
    }

    public RoleName getRoleName() {
        return role.getName();
    }
}
