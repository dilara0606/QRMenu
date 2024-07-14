package com.QRMenu.menu.service;

import com.QRMenu.menu.dto.RestaurantDto;
import com.QRMenu.menu.entity.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<RestaurantDto> getRestaurantInfo();

    RestaurantDto editRestaurant(Integer id, Restaurant restaurant);

    RestaurantDto createRestaurant(Restaurant restaurant);
}
