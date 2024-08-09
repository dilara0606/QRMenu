package com.QRMenu.menu.service;

import com.QRMenu.menu.dto.UserDto;
import com.QRMenu.menu.entity.User;

public interface UserService {
    UserDto getUserInfo(String token);

    void updatePassword(String token, String newPassword);

    UserDto updateProfile(String token, UserDto user);
}
