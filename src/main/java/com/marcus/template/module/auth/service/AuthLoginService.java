package com.marcus.template.module.auth.service;

import com.marcus.template.module.auth.dto.request.LoginRequest;
import com.marcus.template.module.auth.dto.response.LoginResponse;
import com.marcus.template.infrastructure.security.TokenConfig;
import com.marcus.template.shared.exception.InvalidCredentialException;
import com.marcus.template.module.user.api.internal.dto.AuthenticationUserDataResponse;
import com.marcus.template.module.user.api.internal.contract.UserAuthenticationQuery;
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

        String token = tokenGenerator.generateToken(authenticationUser.id(), authenticationUser.companyId(), authenticationUser.role());
        String refreshToken = tokenGenerator.generateRefreshToken(authenticationUser.id(), authenticationUser.companyId());
        refreshTokenManager.create(authenticationUser.id(), refreshToken);
        log.info(
                "Login successful for username={}",
                command.username()
        );
        return new LoginResponse(token, refreshToken);
    }
}
