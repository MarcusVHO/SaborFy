package com.br.marcus.saborfy.containersize.dto;

import lombok.Data;

@Data
public class DeleteContainerSizeResponse {
    private String message;

    public DeleteContainerSizeResponse(String message) {
        this.message = message;
    }
}
