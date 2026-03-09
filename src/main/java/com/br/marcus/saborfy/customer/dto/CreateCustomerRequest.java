package com.br.marcus.saborfy.customer.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class CreateCustomerRequest {
    @NotNull(message = "Para adicionar um novo cliente é necessario preecher todos os campos!")
    private String nome;

    @NotNull(message = "Para adicionar um novo cliente é necessario preecher todos os campos!")
    private int telefone;

    @NotEmpty(message = "Para adicionar um novo cliente é necessario ter no minimo um endereço!")
    private List<AddressRequest> enderecos;

}
