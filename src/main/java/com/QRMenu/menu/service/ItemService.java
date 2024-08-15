package com.QRMenu.menu.service;

import com.QRMenu.menu.dto.CategoryDto;
import com.QRMenu.menu.dto.ItemDto;
import com.QRMenu.menu.entity.Item;
import com.QRMenu.menu.filter.ItemFilter;

import java.util.List;

public interface ItemService {

    ItemDto saveItem(Item item, String token);

    void deleteItem(Integer id);

    ItemDto editItem(Integer id, Item item);

    List<ItemDto> getAll(String token);

    List<ItemDto> searchItem(ItemFilter itemFilter);
}
