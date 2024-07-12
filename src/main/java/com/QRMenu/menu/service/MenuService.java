package com.QRMenu.menu.service;

import com.QRMenu.menu.dto.MenuDto;
import com.QRMenu.menu.entity.Menu;

public interface MenuService {

    void saveMenu(Menu menu);

    MenuDto activateMenu(Integer id);

    MenuDto deactivateMenu(Integer id);

}
