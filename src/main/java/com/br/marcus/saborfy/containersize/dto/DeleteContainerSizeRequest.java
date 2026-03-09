package com.br.marcus.saborfy.containersize.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeleteContainerSizeRequest {
    @NotNull(message = "Para remover um item deve-se passar um id!")
    private Long id;
}
