package com.marcus.saborfy.module.auth.service;

import com.marcus.saborfy.module.auth.dto.response.LoginResponse;
import com.marcus.saborfy.infrastructure.security.TokenConfig;
import com.marcus.saborfy.shared.exception.InvalidRefreshTokenException;
import com.marcus.saborfy.module.auth.entity.RefreshToken;
import com.marcus.saborfy.module.user.api.internal.dto.UserInternalResponse;
import com.marcus.saborfy.module.user.api.internal.contract.UserQueryApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthRefreshService {
    private final TokenConfig tokenGenerator;
    private final RefreshTokenManager refreshTokenManager;
    private final UserQueryApi userQueryApi;

    public AuthRefreshService(TokenConfig tokenGenerator, RefreshTokenManager refreshTokenManager, UserQueryApi userQueryApi) {
        this.tokenGenerator = tokenGenerator;
        this.refreshTokenManager = refreshTokenManager;
        this.userQueryApi = userQueryApi;
    }

    public LoginResponse refreshTokens(String refreshToken) {
        Long id = tokenGenerator.validate(refreshToken);
        log.debug("Refresh token payload is not null for user id={}", id);
        RefreshToken refreshTokenEntity = refreshTokenManager.findByRawToken(refreshToken);
        log.debug("Refresh token exists in database id={}", id);

        if (!refreshTokenEntity.isValid()) {
            throw new InvalidRefreshTokenException();
        }
        UserInternalResponse userResponse = userQueryApi.findById(id);

        refreshTokenEntity.revoke();
        refreshTokenManager.save(refreshTokenEntity);

        String newToken = tokenGenerator.generateToken(userResponse.id(), userResponse.restaurantId(), userResponse.role());
        String newRefreshToken = tokenGenerator.generateRefreshToken(userResponse.id(), userResponse.restaurantId());
        log.info(
                "Refresh successful for username={}",
                userResponse.username()
        );
        refreshTokenManager.create(userResponse.id(), newRefreshToken);
        return new LoginResponse(newToken, newRefreshToken);
    }
}
