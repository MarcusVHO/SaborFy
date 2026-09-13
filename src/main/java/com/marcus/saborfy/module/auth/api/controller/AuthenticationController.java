package com.marcus.saborfy.module.auth.api.controller;

import com.marcus.saborfy.module.auth.dto.request.LoginRequest;
import com.marcus.saborfy.module.auth.dto.request.RefreshTokenRequest;
import com.marcus.saborfy.module.auth.dto.response.LoginResponse;
import com.marcus.saborfy.module.auth.service.AuthLoginService;
import com.marcus.saborfy.module.auth.service.AuthLogoutService;
import com.marcus.saborfy.module.auth.service.AuthRefreshService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@Tag(
        name = "Authentication",
        description = "Endpoints responsible for authentication users."
)
public class AuthenticationController {
    private final AuthRefreshService authRefreshService;
    private final AuthLoginService authLoginService;
    private final AuthLogoutService authLogoutService;

    public AuthenticationController(AuthRefreshService authRefreshService, AuthLoginService authLoginService, AuthLogoutService authLogoutService) {
        this.authRefreshService = authRefreshService;
        this.authLoginService = authLoginService;
        this.authLogoutService = authLogoutService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (
            @Valid @RequestBody LoginRequest request
    ) {
        LoginResponse loginResponse = authLoginService.execute(request);
        return ResponseEntity.ok().body(loginResponse);
    }


    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh (
            @RequestBody RefreshTokenRequest request
    ) {
        LoginResponse loginResponse = authRefreshService.refreshTokens(request.refreshToken());
        return ResponseEntity.ok().body(loginResponse);
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestBody RefreshTokenRequest request
    ) {
        authLogoutService.execute(request.refreshToken());
        return ResponseEntity.ok().build();
    }


}

