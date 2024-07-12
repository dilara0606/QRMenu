package com.QRMenu.menu.controller;

import com.QRMenu.menu.entity.Item;
import com.QRMenu.menu.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/create-item")
    public ResponseEntity<String> createItem(@RequestBody Item item) {
        itemService.saveItem(item);
        return new ResponseEntity<>("Item created successfully", HttpStatus.CREATED);
    }
}
