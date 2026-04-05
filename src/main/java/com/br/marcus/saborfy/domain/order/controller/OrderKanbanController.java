package com.br.marcus.saborfy.domain.order.controller;

import com.br.marcus.saborfy.domain.order.dto.response.OrderKanbanResponse;
import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.enums.OrderStatus;
import com.br.marcus.saborfy.domain.order.service.KanbanOrderService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(path = "/order/kanban")
public class OrderKanbanController {

    private final KanbanOrderService kanbanOrderService;

    public OrderKanbanController(KanbanOrderService kanbanOrderService) {
        this.kanbanOrderService = kanbanOrderService;
    }

    @GetMapping
    public ResponseEntity<OrderKanbanResponse> get(
            @RequestParam @NotNull(message = "Start date is necessary!")Instant startDate,
            @RequestParam @NotNull(message = "End date is necessary!")Instant endDate
            ) {
        List<Order> created = kanbanOrderService.getOrdersForKanban(OrderStatus.CREATED, startDate, endDate);
        List<Order> confirmed = kanbanOrderService.getOrdersForKanban(OrderStatus.CONFIRMED, startDate, endDate);
        List<Order> preparing = kanbanOrderService.getOrdersForKanban(OrderStatus.PREPARING, startDate, endDate);
        List<Order> outForDelivery = kanbanOrderService.getOrdersForKanban(OrderStatus.OUT_FOR_DELIVERY, startDate, endDate);
        List<Order> completed = kanbanOrderService.getOrdersForKanban(OrderStatus.COMPLETED, startDate, endDate);
        List<Order> canceled = kanbanOrderService.getOrdersForKanban(OrderStatus.CANCELED, startDate, endDate);

        return ResponseEntity.ok(new OrderKanbanResponse(created, confirmed, preparing, outForDelivery, completed, canceled));
    }
}
