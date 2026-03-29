package com.br.marcus.saborfy.domain.customer.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record UpdateCustomerRequest(@NotEmpty(message = "Name is necessary!") String name) {
}
