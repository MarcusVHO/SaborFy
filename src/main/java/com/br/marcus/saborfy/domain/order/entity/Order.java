package com.br.marcus.saborfy.domain.order.entity;

import com.br.marcus.saborfy.domain.order.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OrderCustomer customer;

    @Embedded
    private OrderAddress address;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.CREATED;

    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @JsonManagedReference
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private OrderObservation observation;

    @Column(name = "created_by")
    private String userId;

    @Column(name = "latest_update_by")
    private String latestUpdateBy;

    @CreationTimestamp
    private Instant createdAt;



    @Transient
    public BigDecimal getTotalAmount() {
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : orderItems) {
            total = total.add(item.getTotalPrice());
        }

        return total;
    }



    @Transient
    public String getOrderNumber() {
        return "ORD-"+this.id;
    }
}

