package com.br.marcus.saborfy.domain.menu.controller;

import com.br.marcus.saborfy.domain.menu.dto.request.CreateItemMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.request.CreateMenuRequest;
import com.br.marcus.saborfy.domain.menu.dto.response.CreateMenuResponse;
import com.br.marcus.saborfy.domain.menu.dto.response.MenuResponse;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import com.br.marcus.saborfy.domain.menu.service.CreateMenuService;
import com.br.marcus.saborfy.domain.menu.service.DeleteMenuService;
import com.br.marcus.saborfy.domain.menu.service.ListMenuService;
import com.br.marcus.saborfy.domain.menu.service.UpdateMenuService;
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
    private final ListMenuService listMenuService;
    private final CreateMenuService createMenuService;
    private final UpdateMenuService updateMenuService;
    private final DeleteMenuService deleteMenuService;

    public MenuController(ListMenuService listMenuService, CreateMenuService createMenuService, UpdateMenuService updateMenuService, DeleteMenuService deleteMenuService) {
        this.listMenuService = listMenuService;
        this.createMenuService = createMenuService;
        this.updateMenuService = updateMenuService;
        this.deleteMenuService = deleteMenuService;
    }

    @GetMapping
    public ResponseEntity<List<MenuResponse>> list() {
        List<Menu> menus = listMenuService.menuList();

        List<MenuResponse> menuResponses = new ArrayList<>();
        for (Menu menu : menus) {
            MenuResponse menuResponse = new MenuResponse(menu);
            menuResponses.add(menuResponse);
        }
        return ResponseEntity.ok(menuResponses);
    }

    @PostMapping
    public ResponseEntity<CreateMenuResponse> create(@AuthenticationPrincipal AuthenticatedUser user, @Valid @RequestBody CreateMenuRequest request) {
        Menu menu = createMenuService.create(user, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateMenuResponse(menu));
    }

    @PutMapping
    public ResponseEntity<MenuResponse> update(
            @AuthenticationPrincipal AuthenticatedUser user,
            @Valid @RequestBody CreateMenuRequest request,
            @NotNull @RequestParam Long id

    ) {
        Menu menu = updateMenuService.updateMenu(user, request, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MenuResponse(menu));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
            @NotNull @RequestParam Long id
    ) {
        deleteMenuService.deleMenu(id);
        return ResponseEntity.noContent().build();
    }
}
