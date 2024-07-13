package com.QRMenu.menu.controller;

import com.QRMenu.menu.dto.MenuDto;
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

    private final MenuService service;

    @PostMapping("/create-menu")
    public ResponseEntity<String> createMenu(@RequestBody Menu menu) {
        service.saveMenu(menu);
        return new ResponseEntity<>("Menu created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/activate-menu/{id}")
    public MenuDto activateMenu(@PathVariable Integer id) {
        return service.activateMenu(id);
    }

    @GetMapping("/deactivate-menu/{id}")
    public MenuDto deactivateMenu(@PathVariable Integer id) {
        return service.deactivateMenu(id);
    }

    @DeleteMapping("/delete-menu/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable Integer id) {
        service.deleteMenu(id);
        return new ResponseEntity<>("Menu deleted successfully", HttpStatus.OK);
    }

    @PutMapping("edit-menu/{id}")
    public MenuDto editMenu(@PathVariable Integer id, @RequestBody Menu menu) {
        return service.editMenu(id, menu);
    }
}
