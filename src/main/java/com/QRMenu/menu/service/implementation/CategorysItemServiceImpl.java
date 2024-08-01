package com.QRMenu.menu.service.implementation;


import com.QRMenu.menu.dto.CategoriesItemDto;
import com.QRMenu.menu.dto.ItemDto;
import com.QRMenu.menu.entity.Category;
import com.QRMenu.menu.entity.CategoriesItem;
import com.QRMenu.menu.entity.Item;
import com.QRMenu.menu.mapper.CategoriesItemMapper;
import com.QRMenu.menu.mapper.ItemMapper;
import com.QRMenu.menu.repository.CategoryRepository;
import com.QRMenu.menu.repository.CategorysItemRepository;
import com.QRMenu.menu.repository.ItemRepository;
import com.QRMenu.menu.service.CategorysItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategorysItemServiceImpl implements CategorysItemService {

    private final ItemRepository itemRepository;
    private final CategorysItemRepository repository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ItemDto> getItemsByCategory(Integer categoryId) {
        List<CategoriesItem> categoryItems = repository.findByCategoryId(categoryId);
        List<Item> items = categoryItems.stream()
                .map(categoryItem -> itemRepository.findById(categoryItem.getItem().getId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return ItemMapper.convertList(items);
    }

    @Override
    public CategoriesItemDto addItem(Integer categoryId, Integer itemId) {

        Category category = categoryRepository.findByid(categoryId);
        Item item = itemRepository.findByid(itemId);

        CategoriesItem categoriesItem = new CategoriesItem();
        categoriesItem.setCategory(category);
        categoriesItem.setItem(item);
        repository.save(categoriesItem);

        return CategoriesItemMapper.convert(categoriesItem);
    }

    @Override
    public void deleteItem(Integer itemId, Integer categoryId) {
        repository.deleteByItemIdAndCategoryId(itemId, categoryId);
    }
}
