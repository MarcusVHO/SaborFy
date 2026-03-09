package com.br.marcus.saborfy.containersize.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateContainerSizeRequest {

    @NotBlank(message = "Nome do container é obrigatório!")
    private String nome;

    @NotBlank(message = "A capacidade é obrigatório!")
    private String capacidade;

    @NotNull(message = "O preço é obrigatório!")
    private BigDecimal preco;
}
