package com.QRMenu.menu.controller;

import com.QRMenu.menu.dto.MenuDto;
import com.QRMenu.menu.entity.Category;
import com.QRMenu.menu.entity.Menu;
import com.QRMenu.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/create-menu")
    public ResponseEntity<String> createMenu(@RequestBody Menu menu) {
        menuService.saveMenu(menu);
        return new ResponseEntity<>("Menu created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/activate-menu/{id}")
    public MenuDto activateMenu(@PathVariable Integer id) {
        return menuService.activateMenu(id);
    }

    @GetMapping("/deactivate-menu/{id}")
    public MenuDto deactivateMenu(@PathVariable Integer id) {
        return menuService.deactivateMenu(id);
    }

}
