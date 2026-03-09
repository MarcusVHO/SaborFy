package com.br.marcus.saborfy.exception;

public class ApiErrorDetail {

    private String field;
    private String message;

    public ApiErrorDetail(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}