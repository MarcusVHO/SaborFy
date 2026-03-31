package com.br.marcus.saborfy.domain.menu.service;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateMenuRequest;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface UpdateMenuService {
    Menu updateMenu (@AuthenticationPrincipal AuthenticatedUser user, CreateMenuRequest request, Long menuId);
}
