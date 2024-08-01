package com.QRMenu.menu.service;

import com.QRMenu.menu.dto.MenuDto;
import com.QRMenu.menu.entity.Menu;

import java.util.List;

public interface MenuService {

    void saveMenu(Menu menu);

    MenuDto activateMenu(Integer id);

    MenuDto deactivateMenu(Integer id);

    void deleteMenu(Integer id);

    MenuDto editMenu(Integer id, Menu menu);

    MenuDto getActiveMenu();

    List<MenuDto> getAll();
}
