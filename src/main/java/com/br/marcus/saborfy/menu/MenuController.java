package com.br.marcus.saborfy.menu;

import com.br.marcus.saborfy.menu.dto.CreateMenuRequest;
import com.br.marcus.saborfy.menu.dto.DeleteMenuRequest;
import com.br.marcus.saborfy.menu.dto.DeleteMenuResponse;
import com.br.marcus.saborfy.menu.dto.UpdateMenuRequest;
import com.br.marcus.saborfy.menu.entity.Menu;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/menu")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<Menu> create(@RequestBody @Valid CreateMenuRequest request) {
        return ResponseEntity.ok(menuService.createMenu(request));
    }

    @GetMapping
    public ResponseEntity<List<Menu>> list() {
        return ResponseEntity.ok(menuService.getListMenus());
    }

    @DeleteMapping
    public ResponseEntity<DeleteMenuResponse> remove(@RequestBody @Valid DeleteMenuRequest deleteMenuRequest) {
        menuService.removeMenu(deleteMenuRequest.getId());
        DeleteMenuResponse deleteMenuResponse = new DeleteMenuResponse("Item removido com sucesso!");
        return ResponseEntity.ok(deleteMenuResponse);
    }

    @PutMapping
    public ResponseEntity<Menu> update(@RequestParam Long id, @RequestBody UpdateMenuRequest updateMenuRequest) {
        Menu menu = menuService.updateMenu(id, updateMenuRequest);
        return ResponseEntity.ok(menu);
    }
}
