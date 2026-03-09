package com.br.marcus.saborfy.containersize.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateContainerSizeRequest {
    @NotNull(message = "É necessario atulizar todos os campos!")
    private String nome;

    @NotNull(message = "É necessario atulizar todos os campos!")
    private String capacidade;

    @NotNull(message = "É necessario atulizar todos os campos!")
    private BigDecimal preco;
}
