package com.QRMenu.menu.mapper;

import com.QRMenu.menu.dto.CategoriesItemDto;
import com.QRMenu.menu.entity.CategoriesItem;
import com.QRMenu.menu.entity.MenusCategory;

import java.util.ArrayList;
import java.util.List;

public class CategoriesItemMapper {

    public static CategoriesItemDto convert(CategoriesItem categoriesItem) {

        return CategoriesItemDto.builder()
                .itemDto(ItemMapper.convert(categoriesItem.getItem()))
                .categoryDto(CategoryMapper.convert(categoriesItem.getCategory()))
                .build();
    }

    public static List<CategoriesItemDto> convertList(List<CategoriesItem> categoriesItemList) {
        List<CategoriesItemDto> categoriesItemDtoList = new ArrayList<>();
        for (CategoriesItem categoriesItem : categoriesItemList) {
            categoriesItemDtoList.add(convert(categoriesItem));
        }
        return categoriesItemDtoList;
    }
}
