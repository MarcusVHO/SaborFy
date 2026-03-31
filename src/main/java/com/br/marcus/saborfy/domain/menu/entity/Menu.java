package com.br.marcus.saborfy.domain.menu.entity;

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
@Table(name = "menu")
public class Menu {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemMenu> items = new ArrayList<>();

    @Setter
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User user;

    @Setter
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User latestUpdateBy;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
