package com.marcus.template.shared.api.exception.dto;

public record FieldErrorResponse(
        String field,
        String message
) {
}
