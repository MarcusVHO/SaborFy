package com.br.marcus.saborfy.domain.customer.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CreatePhoneRequest(
        @NotEmpty(message = "Number of phone is necessary!") String number
        ) {
}
