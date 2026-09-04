package com.marcus.template.module.auth.api.controller;

import com.marcus.template.module.auth.dto.request.LoginRequest;
import com.marcus.template.module.auth.dto.request.RefreshTokenRequest;
import com.marcus.template.module.auth.dto.response.LoginResponse;
import com.marcus.template.module.auth.service.AuthLoginService;
import com.marcus.template.module.auth.service.AuthLogoutService;
import com.marcus.template.module.auth.service.AuthRefreshService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
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


    @Operation(
            summary = "Login in application",
            description = "Authentic user and return the Access Token And Refresh Token."
    )
    @SecurityRequirements
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Login successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid username or password"
            )
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (
            @Valid @RequestBody LoginRequest request
    ) {
        LoginResponse loginResponse = authLoginService.execute(request);
        return ResponseEntity.ok().body(loginResponse);
    }





    @Operation(
            summary = "Refresh login in application",
            description = "Authentic user and return the Access Token And Refresh Token."
    )
    @SecurityRequirements
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "refresh successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid refresh token"
            )
    })
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh (
            @RequestBody RefreshTokenRequest request
    ) {
        LoginResponse loginResponse = authRefreshService.refreshTokens(request.refreshToken());
        return ResponseEntity.ok().body(loginResponse);
    }


    @Operation(
            summary = "Logout user in application",
            description = "Logout revoke a current token of user."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Logout successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid refresh token"
            )
    })
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestBody RefreshTokenRequest request
    ) {
        authLogoutService.execute(request.refreshToken());
        return ResponseEntity.ok().build();
    }


}

