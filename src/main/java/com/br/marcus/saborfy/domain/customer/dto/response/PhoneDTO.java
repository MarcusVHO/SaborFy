package com.br.marcus.saborfy.domain.customer.dto.response;

import com.br.marcus.saborfy.domain.customer.entity.CustomerPhone;
import lombok.Getter;

import java.time.Instant;

public record PhoneDTO (
    Long id,
    String number,
    Instant createdAt
) {

}
