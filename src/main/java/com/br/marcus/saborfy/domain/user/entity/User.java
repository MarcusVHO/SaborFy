package com.br.marcus.saborfy.domain.user.entity;

import com.br.marcus.saborfy.domain.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Setter
    @Column(nullable = false)
    private String name;


    @Setter
    @Column(nullable = false, unique = true)
    private Long registration;


    @Setter
    @Column(nullable = false)
    private String password;

    @Setter
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return (String.valueOf(registration));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

