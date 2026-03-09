package com.br.marcus.saborfy.menu;

import com.br.marcus.saborfy.exception.exceptions.ConflictException;
import com.br.marcus.saborfy.exception.exceptions.NotFoundException;
import com.br.marcus.saborfy.menu.dto.CreateMenuRequest;
import com.br.marcus.saborfy.menu.dto.MenuItemRequest;
import com.br.marcus.saborfy.menu.dto.UpdateMenuRequest;
import com.br.marcus.saborfy.menu.entity.Menu;
import com.br.marcus.saborfy.menu.entity.MenuItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu createMenu(CreateMenuRequest request) {
        if (menuRepository.existsByNome(request.getNome())) {
            throw new ConflictException("Já existe um menu com esse nome!");
        };
        Menu menu = new Menu();
        menu.setNome(request.getNome());

        List<MenuItem> itens = new ArrayList<>();

        for (MenuItemRequest itemRequest : request.getItens()) {
            MenuItem item = new MenuItem();
            item.setNome(itemRequest.getNome());
            item.setTipo(itemRequest.getTipo());
            item.setPreco(itemRequest.getPreco());
            item.setMenu(menu);
            itens.add(item);
        }
        menu.setItems(itens);

        return menuRepository.save(menu);
    }

    public List<Menu> getListMenus() {
        return  menuRepository.findAll();
    }

    public void removeMenu(Long id) {
        menuRepository.deleteById(id);
    }

    public Menu updateMenu(Long id, UpdateMenuRequest request) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new NotFoundException("Menu não encontrado!"));

        menu.setNome(request.getNome());
        menu.getItems().clear();

        for (MenuItemRequest menuItemRequest : request.getItens()) {
            MenuItem menuItem = new MenuItem();
            menuItem.setNome(menuItemRequest.getNome());
            menuItem.setTipo(menuItemRequest.getTipo());
            menuItem.setPreco(menuItemRequest.getPreco());
            menuItem.setMenu(menu);

            menu.getItems().add(menuItem);
        }
        menuRepository.save(menu);
        return menu;
    }

}
