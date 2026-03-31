package com.br.marcus.saborfy.domain.menu.entity;

import com.br.marcus.saborfy.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "item_menu")
public class ItemMenu {
    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Setter
    private Double price;

    @Setter
    @Column(columnDefinition = "TEXT")
    private String description;

    @Setter
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "created_by", nullable = false)
    private User user;

    @Setter
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User latestUpdateBy;

    @Setter
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
