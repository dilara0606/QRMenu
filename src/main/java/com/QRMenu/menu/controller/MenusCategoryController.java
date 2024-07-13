package com.QRMenu.menu.controller;

import com.QRMenu.menu.dto.MenusCategoryDto;
import com.QRMenu.menu.service.MenusCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class MenusCategoryController {

    private final MenusCategoryService service;

    @PostMapping("/add-category/{menuId}")
    public MenusCategoryDto addCategory(@PathVariable Integer menuId, @RequestParam Integer categoryId) {
        return service.addCategory(menuId, categoryId);
    }

    @DeleteMapping("delete-category/{menuId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer menuId, @RequestParam Integer categoryId) {
        service.deleteCategory(menuId, categoryId);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
}
