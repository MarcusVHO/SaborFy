package com.br.marcus.saborfy.domain.auth.service;

import com.br.marcus.saborfy.domain.auth.dto.request.LoginRequest;
import com.br.marcus.saborfy.domain.auth.dto.response.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationService {
    LoginResponse login (@RequestBody @Valid LoginRequest request);
}
