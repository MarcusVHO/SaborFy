package com.br.marcus.saborfy.domain.customer.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateAddressRequest(
        @NotEmpty(message = "Adress is necessary") String address,
        @NotNull(message = "Number is necessary") int number,
        String complement) {
}
