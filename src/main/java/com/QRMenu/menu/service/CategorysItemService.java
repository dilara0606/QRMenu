package com.QRMenu.menu.service;

import com.QRMenu.menu.dto.CategoriesItemDto;
import com.QRMenu.menu.dto.ItemDto;

import java.util.List;

public interface CategorysItemService {

    List<ItemDto> getItemsByCategory(String token, String category);

    CategoriesItemDto addItem(Integer categoryId, Integer itemId);
}
