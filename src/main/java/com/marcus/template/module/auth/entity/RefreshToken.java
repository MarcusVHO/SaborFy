package com.marcus.template.module.auth.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "token_hash", nullable = false, unique = true)
    private String tokenHash;

    @Column(nullable = false)
    private boolean revoked;

    @CreationTimestamp
    private Instant createdAt;

    public RefreshToken (
            Long userId,
            String tokenHash
    ) {
        this.userId = userId;
        this.tokenHash = tokenHash;
        this.revoked = false;
        this.createdAt = Instant.now();
    }

    public boolean isValid() {
        return !revoked;
    }

    public void revoke() {
        this.revoked = true;
    }
}
