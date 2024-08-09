package com.QRMenu.menu.controller;

import com.QRMenu.menu.dto.UserDto;
import com.QRMenu.menu.entity.User;
import com.QRMenu.menu.mapper.UserMapper;
import com.QRMenu.menu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/info")
    private UserDto getUserInfo (@RequestHeader(name = "Authorization") String token){
        return service.getUserInfo(token);
    }

    @PostMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody String newPassword, @RequestHeader(name = "Authorization") String token) {
        service.updatePassword(token, newPassword);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @PostMapping("/update-profile")
    public UserDto updateProfile(@RequestBody UserDto user, @RequestHeader(name = "Authorization") String token) {
        return service.updateProfile(token, user);
    }

}
