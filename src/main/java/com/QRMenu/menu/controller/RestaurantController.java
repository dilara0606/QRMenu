package com.QRMenu.menu.controller;

import com.QRMenu.menu.dto.RestaurantDto;
import com.QRMenu.menu.entity.Restaurant;
import com.QRMenu.menu.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService service;

    @GetMapping("info")
    public List<RestaurantDto> getRestaurantInfo(){
        return service.getRestaurantInfo();
    }

    @PutMapping("edit-restaurant/{id}")
    public RestaurantDto editRestaurant(@PathVariable Integer id, @RequestBody Restaurant restaurant){
        return service.editRestaurant(id, restaurant);
    }

    @PostMapping("create-restaurant")
    public RestaurantDto createRestaurant(@RequestBody Restaurant restaurant){
        return service.createRestaurant(restaurant);
    }

}
