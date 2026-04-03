package com.br.marcus.saborfy.domain.order.dto.response;

import com.br.marcus.saborfy.domain.order.entity.OrderCustomer;
import lombok.Getter;

@Getter
public class OrderCustomerResponse{
    private final Long customerId;
    private final String customerName;

    public OrderCustomerResponse(OrderCustomer customer) {
        this.customerId = customer.getCustomerId();
        this.customerName = customer.getCustomerName();
    }
}
