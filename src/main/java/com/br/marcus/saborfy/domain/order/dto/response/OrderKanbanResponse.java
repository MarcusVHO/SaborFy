package com.br.marcus.saborfy.domain.order.dto.response;

import com.br.marcus.saborfy.domain.order.entity.Order;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderKanbanResponse {
    private final List<OrderListResponse> created;
    private final List<OrderListResponse> confirmed;
    private final List<OrderListResponse> preparing;
    private final List<OrderListResponse> outForDelivery;
    private final List<OrderListResponse> completed;
    private final List<OrderListResponse> canceled;

    public OrderKanbanResponse(
            List<Order> created,
            List<Order> confirmed,
            List<Order> preparing,
            List<Order> outForDelivery,
            List<Order> completed,
            List<Order> canceled

    ) {
        this.created = createResponse(created);
        this.confirmed = createResponse(confirmed);
        this.preparing = createResponse(preparing);
        this.outForDelivery = createResponse(outForDelivery);
        this.completed = createResponse(completed);
        this.canceled = createResponse(canceled);
    }

    private List<OrderListResponse> createResponse(List<Order> orders) {
        List<OrderListResponse> responses = new ArrayList<>();
        for(Order order : orders) {
            responses.add(new OrderListResponse(order));
        }
        return responses;
    }

}
