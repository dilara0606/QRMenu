package com.QRMenu.menu.mapper;

import com.QRMenu.menu.dto.ItemDto;
import com.QRMenu.menu.entity.Item;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ItemMapper {

    public static ItemDto convert(Item item) {
        String mediaPath = item.getImageUrl();
        String mediaUrl = convertToLocalUrl(mediaPath);

        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .imageUrl(mediaUrl)
                .updatedAt(item.getUpdatedAt())
                .build();
    }

    private static String convertToLocalUrl(String mediaPath) {
        Path imagePath = Paths.get(mediaPath);
        Path desktopPath = Paths.get("C:/Users/Dilara/Desktop");
        Path relativePath = desktopPath.relativize(imagePath);
        String relativePathStr = relativePath.toString().replace("\\", "/");
        return "http://localhost:8088/api/v1/images/" + relativePathStr;
    }

    public static List<ItemDto> convertList(List<Item> itemList) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        for (Item item : itemList) {
            itemDtoList.add(convert(item));
        }
        return itemDtoList;
    }
}
