package com.br.marcus.saborfy.menu;

import com.br.marcus.saborfy.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    boolean existsByNome(String nome);

}
