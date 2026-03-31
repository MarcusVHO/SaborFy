package com.br.marcus.saborfy.domain.menu.service;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateMenuRequest;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;

public interface CreateMenuService {
    Menu create(AuthenticatedUser user, CreateMenuRequest request);
}
