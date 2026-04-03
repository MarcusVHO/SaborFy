package com.br.marcus.saborfy.domain.order.dto.response;

import com.br.marcus.saborfy.domain.order.entity.OrderAddress;
import lombok.Getter;

@Getter
public class OrderAddressResponse{
    private final String street;
    private final Integer number;
    private final String complement;

    public OrderAddressResponse(OrderAddress address) {
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.complement = address.getComplement();
    }
}
