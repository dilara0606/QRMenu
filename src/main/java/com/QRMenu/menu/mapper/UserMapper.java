package com.QRMenu.menu.mapper;

import com.QRMenu.menu.dto.UserDto;
import com.QRMenu.menu.entity.User;

public class UserMapper {

    public static UserDto convert(User user) {

        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .surname(user.getSurname())
                .location(user.getLocation())
                .lastModifiedDate(user.getLastModifiedDate())
                .build();
    }

}
