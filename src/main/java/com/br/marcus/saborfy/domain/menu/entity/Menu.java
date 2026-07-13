package com.br.marcus.saborfy.domain.menu.entity;

import com.br.marcus.saborfy.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "menu")
@NoArgsConstructor
public class Menu {
    public Menu(String name, String createdBy) {
        this.name = name;
        this.createdBy = createdBy;
        this.latestUpdateBy = createdBy;
    }

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
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Setter
    @Column(name = "updated_by", nullable = false)
    private String latestUpdateBy;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    public void changeName(String name, String userId) {
        this.name = name;
        this.latestUpdateBy = userId;
    }
}
