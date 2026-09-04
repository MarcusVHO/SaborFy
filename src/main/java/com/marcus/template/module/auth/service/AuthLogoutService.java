package com.marcus.template.module.auth.service;

import com.marcus.template.module.auth.entity.RefreshToken;
import com.marcus.template.module.auth.repository.RefreshTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthLogoutService {
    private final RefreshTokenManager refreshTokenManager;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthLogoutService(RefreshTokenManager refreshTokenManager, RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenManager = refreshTokenManager;
        this.refreshTokenRepository = refreshTokenRepository;
    }



    public void execute(String refreshToken) {
        RefreshToken refreshTokenEntity =  refreshTokenManager.findByRawToken(refreshToken);
        refreshTokenEntity.revoke();
        refreshTokenRepository.save(refreshTokenEntity);
    }
}
