package com.br.marcus.saborfy.customer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeleteCustomerRequest {
    @NotNull(message = "Para remover um item deve-se passar um id!")
    private Long id;
}
