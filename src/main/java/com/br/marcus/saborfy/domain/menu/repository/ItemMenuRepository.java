package com.br.marcus.saborfy.domain.menu.repository;

import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemMenuRepository extends JpaRepository<ItemMenu, Long> {
}
