package com.QRMenu.menu.controller;

import com.QRMenu.menu.dto.CategoriesItemDto;
import com.QRMenu.menu.dto.ItemDto;
import com.QRMenu.menu.service.CategorysItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CategoriesItemController {

    private final CategorysItemService service;

    @GetMapping("/all-category/{categoryId}")
    public List<ItemDto> getItemsByCategory(@PathVariable Integer categoryId){
        return service.getItemsByCategory(categoryId);
    }

    @GetMapping("/add-item/{categoryId}")
    public CategoriesItemDto addItem(@PathVariable Integer categoryId, @RequestParam Integer itemId){
        return service.addItem(categoryId, itemId);
    }

    @DeleteMapping("delete-item/{categoryId}")
    public ResponseEntity<String> deleteItem(@PathVariable Integer categoryId, @RequestParam Integer itemId) {
        service.deleteItem(itemId, categoryId);
        return new ResponseEntity<>("Item deleted successfully", HttpStatus.OK);
    }
}
