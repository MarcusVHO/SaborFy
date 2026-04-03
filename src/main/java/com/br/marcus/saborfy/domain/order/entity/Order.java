package com.br.marcus.saborfy.domain.order.entity;

import com.br.marcus.saborfy.domain.order.enums.OrderStatus;
import com.br.marcus.saborfy.domain.payment.entity.Payment;
import com.br.marcus.saborfy.domain.payment.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();

    @JsonManagedReference
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private OrderObservation observation;

    @Column(name = "created_by")
    private String userId;

    @Column(name = "latest_update_by")
    private String latestUpdateBy;

    @CreationTimestamp
    private LocalDateTime createdAt;



    @Transient
    public BigDecimal getTotalAmount() {
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : orderItems) {
            total = total.add(item.getTotalPrice());
        }

        return total;
    }

    @Transient
    public BigDecimal getTotalPaid() {
        BigDecimal totalPaid = BigDecimal.ZERO;
        for (Payment payment : payments) {
            if (payment.getStatus() == PaymentStatus.APPROVED) {
                totalPaid = totalPaid.add(payment.getAmount());
            }
        }
        return totalPaid;
    }

    @Transient
    public PaymentStatus getPaymentStatus() {
        BigDecimal total = getTotalAmount();
        BigDecimal paid = getTotalPaid();

        if (this.payments.isEmpty() && this.orderStatus != OrderStatus.CANCELED) {
            return PaymentStatus.PENDING;
        }

        boolean allCancel = this.payments.stream()
                .allMatch(x -> x.getStatus().equals(PaymentStatus.CANCELED));
        if (allCancel) {
            return PaymentStatus.CANCELED;
        }

        boolean allRefunded = this.payments.stream()
                .allMatch(x -> x.getStatus().equals(PaymentStatus.REFUNDED) || x.getStatus().equals(PaymentStatus.CANCELED));
        if (allRefunded) {
            return PaymentStatus.REFUNDED;
        }

        if (paid.compareTo(BigDecimal.ZERO) == 0) {
            return PaymentStatus.PENDING;
        }
        if (paid.compareTo(total) >= 0) {
            return PaymentStatus.APPROVED;
        }

        if(this.orderStatus == OrderStatus.CANCELED) {
            return PaymentStatus.CANCELED;
        }

        return PaymentStatus.PARTIALLY_PAID;
    }

    @Transient
    public String getOrderNumber() {
        return "ORD-"+this.id;
    }
}

