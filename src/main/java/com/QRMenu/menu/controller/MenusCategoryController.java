package com.QRMenu.menu.controller;

import com.QRMenu.menu.dto.MenusCategoryDto;
import com.QRMenu.menu.service.MenusCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class MenusCategoryController {

    private final MenusCategoryService menusCategoryService;

    @PostMapping("/add-category/{menuId}")
    public MenusCategoryDto addCategory(@PathVariable Integer menuId, @RequestParam Integer categoryId) {
        return menusCategoryService.addCategory(menuId, categoryId);
    }
}
