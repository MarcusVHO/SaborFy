package com.br.marcus.saborfy.domain.customer.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "customer")
@NoArgsConstructor
public class Customer {

    public Customer(String name, String createdBy) {
        if (name.isBlank() || createdBy.isBlank()) {
            throw new IllegalArgumentException("Name or created by by must not be null or blank!");
        }
        this.name = name;
        this.createdBy = createdBy;
        this.latestUpdatedBy = createdBy;
    }

    @Id
    @Setter
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
    @Column(nullable = false, name = "created_by")
    private String createdBy;

    @Setter
    @Column(nullable = false, name = "latest_updated_by")
    private String latestUpdatedBy;

    @CreationTimestamp
    private Instant createdAt;

    public void addPhone(String number, String createdBy) {
        CustomerPhone customerPhone = new CustomerPhone(number, this, createdBy);
        this.phones.add(customerPhone);
    }

    public void addAddress(String address, Integer number, String complement, String createdBy) {
        CustomerAddress customerAddress = new CustomerAddress(address, number, complement, this, createdBy);
        this.addresses.add(customerAddress);
    }

    public void changeName(String name) {
        this.name = name;
    }
    public void changeLatestUpdatedBy(String updatedBy) {
        if (!this.latestUpdatedBy.equals(updatedBy)) {
            this.latestUpdatedBy = updatedBy;
        }
    }
}
