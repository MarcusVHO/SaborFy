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
@Table(name = "customer_address")
@NoArgsConstructor
public class CustomerAddress {
    public CustomerAddress(String address, Integer number, String complement, Customer customer, String createdBy) {
        if (address.isBlank() || number == null || complement.isEmpty() || customer == null || createdBy.isBlank()) {
            throw new IllegalArgumentException("All arguments must not be null or blank!");
        }
        this.address = address;
        this.number = number;
        this.complement = complement;
        this.customer = customer;
        this.createdBy = createdBy;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String address;

    @Setter
    @Column(nullable = false)
    private Integer number;

    @Setter
    private String complement;

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
