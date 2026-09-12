package com.marcus.saborfy.module.auth.service;

import com.marcus.saborfy.module.auth.dto.request.LoginRequest;
import com.marcus.saborfy.module.auth.dto.response.LoginResponse;
import com.marcus.saborfy.infrastructure.security.TokenConfig;
import com.marcus.saborfy.shared.exception.InvalidCredentialException;
import com.marcus.saborfy.module.user.api.internal.dto.AuthenticationUserDataResponse;
import com.marcus.saborfy.module.user.api.internal.contract.UserAuthenticationQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AuthLoginService {
    private final TokenConfig tokenGenerator;
    private final RefreshTokenManager refreshTokenManager;
    private final PasswordEncoder passwordVerifier;
    private final UserAuthenticationQuery userAuthenticationQuery;

    public AuthLoginService(TokenConfig tokenGenerator, RefreshTokenManager refreshTokenManager, PasswordEncoder passwordVerifier, UserAuthenticationQuery userAuthenticationQuery) {
        this.tokenGenerator = tokenGenerator;
        this.refreshTokenManager = refreshTokenManager;
        this.passwordVerifier = passwordVerifier;
        this.userAuthenticationQuery = userAuthenticationQuery;
    }

    @Transactional
    public LoginResponse execute(LoginRequest command) {
        AuthenticationUserDataResponse authenticationUser = userAuthenticationQuery.findByUsername(command.username());


        if (!authenticationUser.active()
            || !passwordVerifier.matches(
                command.password(),
                authenticationUser.passwordHash())
        ) {
            throw new InvalidCredentialException();
        }

        String token = tokenGenerator.generateToken(authenticationUser.id(), authenticationUser.restaurantId(), authenticationUser.role());
        String refreshToken = tokenGenerator.generateRefreshToken(authenticationUser.id(), authenticationUser.restaurantId());
        refreshTokenManager.create(authenticationUser.id(), refreshToken);
        log.info(
                "Login successful for registration={} role={}",
                command.username(),
                authenticationUser.role()
        );
        return new LoginResponse(token, refreshToken);
    }
}
