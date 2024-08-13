package com.QRMenu.menu.mapper;

import com.QRMenu.menu.dto.MenuDto;
import com.QRMenu.menu.entity.Menu;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MenuMapper {

    public static MenuDto convert(Menu menu) {
        String mediaPath = menu.getImageUrl();
        String mediaUrl = convertToLocalUrl(mediaPath);

        return MenuDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .description(menu.getDescription())
                .imageUrl(mediaUrl)
                .isActive(menu.isActive())
                .createdAt(menu.getCreatedAt())
                .updatedAt(menu.getUpdatedAt())
                .menusCategoryDtoList(MenusCategoryMapper.convertListWithoutMenuDto(menu.getMenusCategoryList()))
                .build();
    }

    private static String convertToLocalUrl(String mediaPath) {
        Path imagePath = Paths.get(mediaPath);
        Path desktopPath = Paths.get("C:/Users/Dilara/Desktop");
        Path relativePath = desktopPath.relativize(imagePath);
        String relativePathStr = relativePath.toString().replace("\\", "/");
        return "http://localhost:8088/api/v1/images/" + relativePathStr;
    }

    public static List<MenuDto> convertList(List<Menu> menuList) {
        List<MenuDto> menuDtoList = new ArrayList<>();
        for (Menu menu : menuList) {
            menuDtoList.add(convert(menu));
        }
        return menuDtoList;
    }
}
