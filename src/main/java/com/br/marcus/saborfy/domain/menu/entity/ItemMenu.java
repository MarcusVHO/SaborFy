package com.br.marcus.saborfy.domain.menu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Entity
@Table(name = "item_menu")
@NoArgsConstructor
public class ItemMenu {
    public ItemMenu(String name, BigDecimal price, String description, String createdBy, Menu menu) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.createdBy = createdBy;
        this.latestUpdateBy = createdBy;
        this.menu = menu;
    }

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Setter
    private BigDecimal price;

    @Setter
    @Column(columnDefinition = "TEXT")
    private String description;

    @Setter
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Setter
    @Column(name = "updated_by")
    private String latestUpdateBy;

    @Setter
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @CreationTimestamp
    private Instant createdAt;


    public void changeName(String name, String userId) {
        this.name = name;
        this.latestUpdateBy = userId;
    }

    public void changePrice(BigDecimal price, String userId) {
        this.price = price;
        this.latestUpdateBy = userId;
    }

    public void changeDescription(String description, String userId) {
        this.description = description;
        this.latestUpdateBy = userId;
    }
}
