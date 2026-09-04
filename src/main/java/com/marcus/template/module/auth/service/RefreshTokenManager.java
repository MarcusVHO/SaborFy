package com.marcus.template.module.auth.service;

import com.marcus.template.infrastructure.security.Sha256TokenHasher;
import com.marcus.template.shared.exception.InvalidRefreshTokenException;
import com.marcus.template.module.auth.entity.RefreshToken;
import com.marcus.template.module.auth.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenManager {
    private final RefreshTokenRepository refreshTokenRepository;
    private final Sha256TokenHasher tokenHasher;

    public RefreshTokenManager(RefreshTokenRepository refreshTokenRepository, Sha256TokenHasher tokenHasher) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.tokenHasher = tokenHasher;
    }

    public RefreshToken findByRawToken(String rawRefreshToken) {
        String tokenHashed = tokenHasher.hash(rawRefreshToken);
        return refreshTokenRepository
                .findByTokenHash(tokenHashed)
                .orElseThrow(InvalidRefreshTokenException::new);
    }

    public void create(Long userId, String rawRefreshToken) {
        String tokenHashed = tokenHasher.hash(rawRefreshToken);
        RefreshToken refreshToken = new RefreshToken(
                userId,
                tokenHashed
        );
        refreshTokenRepository.save(refreshToken);
    }

    public void save(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }
}

