package com.br.marcus.saborfy.domain.customer.entity;

import com.br.marcus.saborfy.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Entity
@Table(name = "customer_address")
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String address;

    @Setter
    @Column(nullable = false)
    private int number;

    @Setter
    private String complement;

    @Setter
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Setter
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "created_by", nullable = false)
    private User user;

    @CreationTimestamp
    private Instant createdAt;
}
