package com.br.marcus.saborfy.domain.auth.controller;

import com.br.marcus.saborfy.domain.auth.dto.request.LoginRequest;
import com.br.marcus.saborfy.domain.auth.dto.response.LoginResponse;
import com.br.marcus.saborfy.domain.auth.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));

    }
}
