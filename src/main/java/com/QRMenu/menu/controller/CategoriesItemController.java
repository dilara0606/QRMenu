package com.QRMenu.menu.controller;

import com.QRMenu.menu.dto.CategoriesItemDto;
import com.QRMenu.menu.dto.ItemDto;
import com.QRMenu.menu.service.CategorysItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CategoriesItemController {

    private final CategorysItemService categorysItemService;

    @GetMapping("/all-category")
    public List<ItemDto> getItemsByCategory(@RequestHeader(name = "Authorization") String token, @PathVariable String category){
        return categorysItemService.getItemsByCategory(token, category);
    }

    @GetMapping("/add-item/{categoryId}")
    public CategoriesItemDto addItem(@PathVariable Integer categoryId, @RequestParam Integer itemId){
        return categorysItemService.addItem(categoryId, itemId);
    }
}
