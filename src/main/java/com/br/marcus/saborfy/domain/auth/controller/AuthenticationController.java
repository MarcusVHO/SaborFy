package com.br.marcus.saborfy.domain.auth.controller;

import com.br.marcus.saborfy.domain.auth.dto.request.LoginRequest;
import com.br.marcus.saborfy.domain.auth.dto.response.LoginResponse;
import com.br.marcus.saborfy.domain.auth.service.AuthenticationService;
import com.br.marcus.saborfy.infra.security.config.TokenConfig;
import com.br.marcus.saborfy.domain.user.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthenticationController(AuthenticationService authenticationService, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody @Valid LoginRequest request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.registration(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token));

    }
}
