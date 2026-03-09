package com.br.marcus.saborfy.menu.dto;

import com.br.marcus.saborfy.menu.enums.ItemTipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuItemRequest {
    @NotBlank(message = "O item precisa ter um nome!")
    private String nome;
    @NotNull(message = "O item precisa ter um tipo!")
    private ItemTipo tipo;

    private BigDecimal preco;

}
