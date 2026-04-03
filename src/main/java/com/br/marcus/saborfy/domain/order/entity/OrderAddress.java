package com.br.marcus.saborfy.domain.order.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class OrderAddress {
    private String street;
    private int number;
    private String complement;
}
