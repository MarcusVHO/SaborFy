package com.marcus.saborfy.module.auth.dto.response;

public record LoginResponse(
        String accessToken,
        String refreshToken
){
}
