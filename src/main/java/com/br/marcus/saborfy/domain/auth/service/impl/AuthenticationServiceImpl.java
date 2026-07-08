package com.br.marcus.saborfy.domain.auth.service.impl;

import com.br.marcus.saborfy.domain.auth.dto.request.LoginRequest;
import com.br.marcus.saborfy.domain.auth.dto.response.LoginResponse;
import com.br.marcus.saborfy.domain.auth.interfaces.AuthTokenDetails;
import com.br.marcus.saborfy.domain.auth.service.AuthenticationService;
import com.br.marcus.saborfy.domain.user.entity.User;
import com.br.marcus.saborfy.infra.security.config.TokenConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.registration(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        AuthTokenDetails userDetails = (AuthTokenDetails) authentication.getPrincipal();
        assert userDetails != null;
        String token = tokenConfig.generateToken(userDetails);
        return new LoginResponse(token);
    }
}
