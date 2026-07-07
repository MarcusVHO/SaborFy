package com.br.marcus.saborfy.domain.menu.service;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateMenuRequest;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface MenuService {
    Menu create(AuthenticatedUser user, CreateMenuRequest request);
    void deleMenu(Long menuId);
    List<Menu> menuList();
    Menu updateMenu (@AuthenticationPrincipal AuthenticatedUser user, CreateMenuRequest request, Long menuId);

}
