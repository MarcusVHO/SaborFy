package com.marcus.template.shared.exception;

public class InvalidRefreshTokenException extends RuntimeException {
    public InvalidRefreshTokenException() {
        super("Invalid refresh token");
    }
}
