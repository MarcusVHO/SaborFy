package com.br.marcus.saborfy.domain.auth.service;

import com.br.marcus.saborfy.domain.auth.dto.request.LoginRequest;
import com.br.marcus.saborfy.domain.auth.dto.response.TokenDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationService {
    TokenDTO login (@RequestBody @Valid LoginRequest request);
}
