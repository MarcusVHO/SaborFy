package com.br.marcus.saborfy.customer.dto;

import lombok.Data;

@Data
public class DeleteCustomerResponse {

    private String message;

    public DeleteCustomerResponse(String message) {
        this.message = message;
    }
}
