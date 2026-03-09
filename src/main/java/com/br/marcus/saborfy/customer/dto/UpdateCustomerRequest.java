package com.br.marcus.saborfy.customer.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UpdateCustomerRequest {

    @NotNull(message = "É necessario atulizar todos os campos!")
    private String nome;

    @NotNull(message = "É necessario atulizar todos os campos!")
    private int telefone;

    @NotEmpty(message = "Deve ser passado pelo menos um item!")
    private List<AddressRequest> enderecos;
}
