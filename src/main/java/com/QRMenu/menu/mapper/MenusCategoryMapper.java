package com.QRMenu.menu.mapper;

import com.QRMenu.menu.dto.MenusCategoryDto;
import com.QRMenu.menu.entity.MenusCategory;

import java.util.ArrayList;
import java.util.List;

public class MenusCategoryMapper {

    public static MenusCategoryDto convert(MenusCategory menusCategory) {

        return MenusCategoryDto.builder()
                .menuDto(MenuMapper.convert(menusCategory.getMenu()))
                .categoryDto(CategoryMapper.convert(menusCategory.getCategory()))
                .build();
    }

    public static List<MenusCategoryDto> convertList(List<MenusCategory> menusCategories) {
        List<MenusCategoryDto> menusCategoryDtoList = new ArrayList<>();
        for (MenusCategory menusCategory : menusCategories) {
            menusCategoryDtoList.add(convert(menusCategory));
        }
        return menusCategoryDtoList;
    }
}
