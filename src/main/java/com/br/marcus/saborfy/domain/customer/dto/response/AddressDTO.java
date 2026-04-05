package com.br.marcus.saborfy.domain.customer.dto.response;

import com.br.marcus.saborfy.domain.customer.entity.CustomerAddress;
import lombok.Getter;

import java.time.Instant;

@Getter
public class AddressDTO {
    private final long id;
    private final String address;
    private final int number;
    private final String complement;
    private final Instant createdAt;

    public AddressDTO(CustomerAddress customerAddress) {
        this.id = customerAddress.getId();
        this.address = customerAddress.getAddress();
        this.number = customerAddress.getNumber();
        this.complement = customerAddress.getComplement();
        this.createdAt = customerAddress.getCreatedAt();
    }
}
