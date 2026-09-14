package com.marcus.saborfy.module.payment.entity;

import com.marcus.saborfy.module.payment.enums.PaymentMethods;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long tabId;

    @Column(nullable = false)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethods paymentMethod;

    private String observation;

    @Column(nullable = false)
    private Long registeredBy;

    @Column(nullable = false)
    private Long restaurantId;

    @CreationTimestamp
    private LocalDateTime created_at;
}
