package com.br.marcus.saborfy.domain.customer.dto.response;
import java.time.Instant;


public record AddressDTO (
        Long id,
        String address,
        Integer number,
        String complement,
        Instant createdAt
    ) {}
