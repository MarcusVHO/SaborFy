package com.br.marcus.saborfy.menu.dto;

import lombok.Getter;
import lombok.Setter;

public class DeleteMenuResponse {
    @Getter
    @Setter
    private String message;

    public DeleteMenuResponse(String message) {
        this.message = message;
    }
}
