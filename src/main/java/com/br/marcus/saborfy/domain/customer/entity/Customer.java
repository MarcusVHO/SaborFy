package com.br.marcus.saborfy.domain.customer.entity;

import com.br.marcus.saborfy.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "customer")
public class Customer {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerPhone> phones = new ArrayList<>();

    @Column(nullable = false)
    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerAddress> addresses = new ArrayList<>();

    @Setter
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "created_by", nullable = false)
    private User user;

    @Setter
    @ManyToOne
    @JoinColumn(name = "updated_by", nullable = false)
    private User latestUpdateBy;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
