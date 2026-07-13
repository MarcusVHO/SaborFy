package com.br.marcus.saborfy.domain.menu.controller;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateItemMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.request.CreateMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.request.UpdateMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.response.CreateMenuResponse;
import com.br.marcus.saborfy.domain.menu.dto.response.MenuResponse;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.domain.menu.service.*;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/menu")
public class MenuController {
    private final MenuService menuService;
    public MenuController(MenuService menuService) {

        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<MenuResponse>> list() {
        List<Menu> menus = menuService.menuList();

        List<MenuResponse> menuResponses = new ArrayList<>();
        for (Menu menu : menus) {
            MenuResponse menuResponse = new MenuResponse(menu);
            menuResponses.add(menuResponse);
        }
        return ResponseEntity.ok(menuResponses);
    }

    @PostMapping
    public ResponseEntity<CreateMenuResponse> create(@AuthenticationPrincipal AuthenticatedUser user, @Valid @RequestBody CreateMenuRequest request) {
        Menu menu = menuService.create(user, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateMenuResponse(menu));
    }

    @PutMapping
    public ResponseEntity<MenuResponse> update(
            @AuthenticationPrincipal AuthenticatedUser user,
            @Valid @RequestBody UpdateMenuRequest request,
            @NotNull @RequestParam Long id

    ) {
        Menu menu = menuService.updateMenu(user, request, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MenuResponse(menu));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
            @NotNull @RequestParam Long id
    ) {
        menuService.deleMenu(id);
        return ResponseEntity.noContent().build();
    }
}
