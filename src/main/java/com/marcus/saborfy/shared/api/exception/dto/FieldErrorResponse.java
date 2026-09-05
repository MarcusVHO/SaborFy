package com.marcus.saborfy.shared.api.exception.dto;

public record FieldErrorResponse(
        String field,
        String message
) {
}
