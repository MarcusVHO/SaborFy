package com.br.marcus.saborfy.domain.customer.dto.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateCustomerRequest (
    @NotEmpty(message = "Name is necessary!") String name,
    @NotEmpty(message = "One address is necessary!") List<CreateAddressRequest> addresses,
    @NotEmpty(message = "One phone is necessary!") List<CreatePhoneRequest> phones
) {
}
