package com.br.marcus.saborfy.menu.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false, unique = true)
    private String nome;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Setter
    @JsonManagedReference
    @OneToMany(mappedBy = "menu", cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> Items;

}
