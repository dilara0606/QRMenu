package com.QRMenu.menu.service.implementation;

import com.QRMenu.menu.dto.UserDto;
import com.QRMenu.menu.entity.User;
import com.QRMenu.menu.mapper.UserMapper;
import com.QRMenu.menu.repository.UserRepository;
import com.QRMenu.menu.security.JwtService;
import com.QRMenu.menu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUserInfo(String token) {

        final String jwt = token.substring(7);
        String email = jwtService.extractUsername(jwt);

        return UserMapper.convert(repository.findByemail(email));
    }

    @Override
    public void updatePassword(String token, String newPassword) {

        final String jwt = token.substring(7);
        String email = jwtService.extractUsername(jwt);

        User user = repository.findByemail(email);
        user.setPasswordHash(passwordEncoder.encode(newPassword));
        user.setLastModifiedDate(LocalDateTime.now());

        repository.save(user);
    }

    @Override
    public UserDto updateProfile(String token, UserDto user) {
        final String jwt = token.substring(7);
        String email = jwtService.extractUsername(jwt);

        User oldUser = repository.findByemail(email);

        oldUser.setLastModifiedDate(LocalDateTime.now());
        oldUser.setEmail(user.getEmail());
        oldUser.setLocation(user.getLocation());
        oldUser.setName(user.getName());
        oldUser.setSurname(user.getSurname());
        oldUser.setPhone(user.getPhone());

        return UserMapper.convert(repository.save(oldUser));
    }
}
