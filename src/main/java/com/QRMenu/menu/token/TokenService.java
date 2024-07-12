package com.QRMenu.menu.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenService {

    private final TokenRepository repository;

    void saveToken(Token token) {
        repository.save(token);
    }
}
