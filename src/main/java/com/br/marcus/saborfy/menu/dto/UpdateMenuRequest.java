package com.br.marcus.saborfy.menu.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.util.List;
@Data
public class UpdateMenuRequest {


    @NotNull(message = "É necessario atulizar todos os campos!")
    private String nome;

    @NotEmpty(message = "Deve ser passado pelo menos um item!")
    private List<MenuItemRequest> itens;
}
