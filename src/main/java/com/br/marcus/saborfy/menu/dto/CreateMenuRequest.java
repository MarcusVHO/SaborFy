package com.br.marcus.saborfy.menu.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


import java.util.List;

@Data
public class CreateMenuRequest {
    @NotBlank(message = "Nome do menu é obrigatório!")
    private String nome;
    @Valid
    @NotEmpty(message = "O menu precisa ter no minimo um item!")
    private List<MenuItemRequest> itens;
}
