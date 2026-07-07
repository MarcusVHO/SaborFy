package com.br.marcus.saborfy.domain.order.controller;

import com.br.marcus.saborfy.domain.order.dto.request.CreateOrderRequest;
import com.br.marcus.saborfy.domain.order.dto.request.OrderFilterRequest;
import com.br.marcus.saborfy.domain.order.dto.request.UpdateOrderRequest;
import com.br.marcus.saborfy.domain.order.dto.response.OrderListResponse;
import com.br.marcus.saborfy.domain.order.dto.response.OrderResponse;
import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.service.OrderService;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestBody CreateOrderRequest request
            ) {
        Order order = orderService.createOrder(user, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderResponse(order));
    }

    @PostMapping(path = "/list")
    public ResponseEntity<List<OrderListResponse>> listItems (
            @RequestBody OrderFilterRequest request
            ) {
        List<Order> orderList = orderService.findOrders(request);
        return ResponseEntity.ok(orderList.stream().map(OrderListResponse::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> get(
            @PathVariable Long id
    ) {
        Order order =  orderService.getOrder(id);
        return ResponseEntity.ok(new OrderResponse(order));
    }

    @DeleteMapping
    public ResponseEntity<Void> cancel (
            @AuthenticationPrincipal AuthenticatedUser user,
            @NotNull(message = "Order ID is necessary!") Long orderId
    ){
        orderService.cancelOrder(user, orderId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<OrderResponse> update(
            @AuthenticationPrincipal AuthenticatedUser user,
            @NotNull(message = "Order ID is necessary!") Long orderId,
            @RequestBody UpdateOrderRequest request
            ) {
        Order order = orderService.updateOrderAddress(user, request, orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderResponse(order));
    }


}
