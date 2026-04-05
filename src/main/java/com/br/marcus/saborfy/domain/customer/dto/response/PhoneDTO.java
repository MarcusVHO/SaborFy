package com.br.marcus.saborfy.domain.customer.dto.response;

import com.br.marcus.saborfy.domain.customer.entity.CustomerPhone;
import lombok.Getter;

import java.time.Instant;

@Getter
public class PhoneDTO {
    private final Long id;
    private final String number;
    private final Instant createdAt;


    public PhoneDTO(CustomerPhone customerPhone) {
         this.id = customerPhone.getId();
         this.number = customerPhone.getNumber();
         this.createdAt = customerPhone.getCreatedAt();
    }
}
