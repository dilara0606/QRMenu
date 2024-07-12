package com.QRMenu.menu.mapper;

import com.QRMenu.menu.dto.MenuDto;
import com.QRMenu.menu.entity.Menu;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MenuMapper {

    public static MenuDto convert(Menu menu) {
        String mediaPath = menu.getImageUrl();
        String mediaUrl = convertToLocalUrl(mediaPath);

        return MenuDto.builder()
                .name(menu.getName())
                .description(menu.getDescription())
                .imageUrl(mediaUrl)
                .isActive(menu.isActive())
                .build();
    }

    private static String convertToLocalUrl(String mediaPath) {
        Path imagePath = Paths.get(mediaPath);
        Path desktopPath = Paths.get("C:/Users/oğuz/Desktop");
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
