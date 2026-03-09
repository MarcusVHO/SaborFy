package com.br.marcus.saborfy.menu.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class DeleteMenuRequest {
    @Getter
    @Setter
    @NotNull(message = "Para remover um item deve-se passar um id!")
    private Long id;
}
