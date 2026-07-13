package com.br.marcus.saborfy.domain.menu.service;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.request.UpdateMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.response.MenuResponse;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface MenuService {
    MenuResponse create(AuthenticatedUser user, CreateMenuRequest request);
    void deleMenu(Long menuId);
    List<MenuResponse> menuList();
    MenuResponse updateMenu (AuthenticatedUser user, UpdateMenuRequest request, Long menuId);

}
