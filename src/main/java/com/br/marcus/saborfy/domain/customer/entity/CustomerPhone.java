package com.br.marcus.saborfy.domain.customer.entity;

import com.br.marcus.saborfy.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Entity
@Table(name = "customer_phone")
@NoArgsConstructor
public class CustomerPhone {
    public CustomerPhone(String number, Customer customer, String createdBy) {
        if (number.isEmpty() || customer == null || createdBy.isEmpty()) {
            throw new IllegalArgumentException("All arguments must be non-null and non-empty!");
        }
        this.number = number;
        this.customer = customer;
        this.createdBy = createdBy;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Column(nullable = false)
    private String number;

    @Setter
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Setter
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @CreationTimestamp
    private Instant createdAt;
}
