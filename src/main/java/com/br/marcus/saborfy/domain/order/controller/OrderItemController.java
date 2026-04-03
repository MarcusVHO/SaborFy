package com.br.marcus.saborfy.domain.order.controller;

import com.br.marcus.saborfy.domain.order.dto.request.CreateOrderItemRequest;
import com.br.marcus.saborfy.domain.order.dto.response.OrderResponse;
import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.service.CreateOrderItemService;
import com.br.marcus.saborfy.domain.order.service.DeleteOrderItemService;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/order/item")
public class OrderItemController {
    private final CreateOrderItemService createOrderItemService;
    private final DeleteOrderItemService deleteOrderItemService;

    public OrderItemController(CreateOrderItemService createOrderItemService, DeleteOrderItemService deleteOrderItemService) {
        this.createOrderItemService = createOrderItemService;
        this.deleteOrderItemService = deleteOrderItemService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestBody List<CreateOrderItemRequest> items,
            @RequestParam @NotNull(message = "Order ID is necessary!") Long orderId
    ) {
        Order order =  createOrderItemService.createItem(user, orderId, items);
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderResponse(order));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
            @NotNull(message = "Oder ID is necessary!") Long orderId,
            @NotNull(message = "Item ID is necessary!") Long itemId
    ) {
        deleteOrderItemService.deleteOrderItem(orderId, itemId);
        return ResponseEntity.noContent().build();
    }
}
