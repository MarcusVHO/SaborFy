package com.marcus.template.module.auth.dto.response;

public record LoginResponse(
        String accessToken,
        String refreshToken
){
}
