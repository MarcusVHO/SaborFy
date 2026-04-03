package com.br.marcus.saborfy.domain.order.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED("created"),
    CONFIRMED("confirmed"),
    OUT_FOR_DELIVERY("outForDelivery"),
    DELIVERED("delivered"),
    CANCELED("canceled");

    private final String status;

    OrderStatus(String status) {this.status = status;}

}
