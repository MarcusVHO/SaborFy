package com.br.marcus.saborfy.exception;

import lombok.Getter;

@Getter
public class ApiErrorDetail {

    private String field;
    private String message;

    public ApiErrorDetail(String field, String message) {
        this.field = field;
        this.message = message;
    }

}