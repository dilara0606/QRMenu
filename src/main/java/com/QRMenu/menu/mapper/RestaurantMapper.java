package com.QRMenu.menu.mapper;

import com.QRMenu.menu.dto.ItemDto;
import com.QRMenu.menu.dto.RestaurantDto;
import com.QRMenu.menu.entity.Item;
import com.QRMenu.menu.entity.Restaurant;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RestaurantMapper {

    public static RestaurantDto convert(Restaurant restaurant) {
        String mediaPath = restaurant.getImageUrl();
        String mediaUrl = convertToLocalUrl(mediaPath);

        return RestaurantDto.builder()
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .email(restaurant.getEmail())
                .phone(restaurant.getPhone())
                .imageUrl(mediaUrl)
                .build();
    }

    private static String convertToLocalUrl(String mediaPath) {
        Path imagePath = Paths.get(mediaPath);
        Path desktopPath = Paths.get("C:/Users/Dilara/Desktop");
        Path relativePath = desktopPath.relativize(imagePath);
        String relativePathStr = relativePath.toString().replace("\\", "/");
        return "http://localhost:8088/api/v1/images/" + relativePathStr;
    }

    public static List<RestaurantDto> convertList(List<Restaurant> restaurantList) {
        List<RestaurantDto> restaurantDtoList = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            restaurantDtoList.add(convert(restaurant));
        }
        return restaurantDtoList;
    }
}
