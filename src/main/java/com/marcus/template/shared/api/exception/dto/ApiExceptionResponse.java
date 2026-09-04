package com.marcus.template.shared.api.exception.dto;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

public record ApiExceptionResponse (
        Instant timestamp,
        int statusCode,
        HttpStatus error,
        String message,
        String path,
        List<FieldErrorResponse> fieldErrors
){
    public static ApiExceptionResponse generateWithFields(HttpStatus status, HttpServletRequest request, String message, List<FieldErrorResponse> fieldErrors) {
        return new ApiExceptionResponse(
                Instant.now(),
                status.value(),
                status,
                message,
                request.getRequestURI(),
                fieldErrors
        );
    }

    public static ApiExceptionResponse generate(HttpStatus status, HttpServletRequest request, String message) {
        return new ApiExceptionResponse(
                Instant.now(),
                status.value(),
                status,
                message,
                request.getRequestURI(),
                null
        );
    }
}

