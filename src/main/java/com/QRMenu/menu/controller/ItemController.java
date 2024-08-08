package com.QRMenu.menu.controller;

import com.QRMenu.menu.dto.ItemDto;
import com.QRMenu.menu.entity.Item;
import com.QRMenu.menu.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;

    @PostMapping("/create-item")
    public ItemDto createItem(@RequestBody Item item, @RequestHeader(name = "Authorization") String token) {
       return service.saveItem(item, token);
    }

    @DeleteMapping("/delete-item/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Integer id) {
        service.deleteItem(id);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/edit-item/{id}")
    public ItemDto editItem(@PathVariable Integer id, @RequestBody Item item) {
        return service.editItem(id,item);
    }

    @GetMapping("/all-item")
    public List<ItemDto> getAllItem(@RequestHeader(name = "Authorization") String token) {
        return service.getAll(token);
    }
}
