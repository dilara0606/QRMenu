package com.QRMenu.menu.service;

import com.QRMenu.menu.dto.MenusCategoryDto;

public interface MenusCategoryService {

    MenusCategoryDto addCategory(Integer menuId, Integer categoryId);

    void deleteCategory(Integer menuId, Integer categoryId);
}
