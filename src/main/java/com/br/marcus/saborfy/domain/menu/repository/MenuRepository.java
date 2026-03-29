package com.br.marcus.saborfy.domain.menu.repository;

import com.br.marcus.saborfy.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
