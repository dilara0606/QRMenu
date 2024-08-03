package com.QRMenu.menu.service;

import com.QRMenu.menu.dto.CategoriesItemDto;
import com.QRMenu.menu.dto.ItemDto;

import java.util.List;

public interface CategorysItemService {

    List<ItemDto> getItemsByCategory(Integer categoryId);

    void addItem(Integer categoryId, Integer itemId);

    void deleteItem(Integer itemId, Integer categoryId);

    List<CategoriesItemDto> getCategoriesByItem(Integer itemId);
}
