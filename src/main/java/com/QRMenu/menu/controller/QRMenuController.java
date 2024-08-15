package com.QRMenu.menu.controller;

import com.QRMenu.menu.dto.MenuDto;
import com.QRMenu.menu.entity.Menu;
import com.QRMenu.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class QRMenuController {

    private final MenuService service;

    @GetMapping("get-active-menu")
    public MenuDto getActiveMenu() {
        return service.getActiveMenu();
    }
}
