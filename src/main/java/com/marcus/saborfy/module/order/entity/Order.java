package com.marcus.saborfy.module.order.entity;

import com.marcus.saborfy.module.order.status.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    private String observation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private boolean delivery = false;

    @Column(nullable = false)
    private Long tabId;

    @Column(nullable = false)
    private Long restaurantId;

    @Column(nullable = false)
    private Long createdBy;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime canceledAt;

    private Long canceledBy;

    private String cancelReason;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();






}
