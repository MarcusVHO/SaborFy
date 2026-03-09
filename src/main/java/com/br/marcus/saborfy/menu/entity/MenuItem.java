package com.br.marcus.saborfy.menu.entity;

import com.br.marcus.saborfy.menu.enums.ItemTipo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String nome;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemTipo tipo;

    @Setter
    @Getter
    @Column(nullable = false)
    private BigDecimal preco;

    @Setter
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
