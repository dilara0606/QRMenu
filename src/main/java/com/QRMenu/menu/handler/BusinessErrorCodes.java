package com.QRMenu.menu.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum BusinessErrorCodes {

    NO_CODE(0, NOT_IMPLEMENTED, "No Code!"),
    INCORRECT_CURRENT_PASSWORD(300, BAD_REQUEST, "Current password is incorrect!"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, BAD_REQUEST, "The new password does not match!"),
    ACCOUNT_LOCKED(302, FORBIDDEN, "User account is locked!"),
    ACCOUNT_DISABLED(303, FORBIDDEN, "User account is disabled!"),
    BAD_CREDENTIALS(304, FORBIDDEN, "Email and / or password is incorrect!"),
    SAME_USERNAME(305, BAD_REQUEST, "This username already in exist!"),
    SAME_EMAIL(306, BAD_REQUEST, "This email already in exist!")

    ;

    @Getter
    private final int code;
    @Getter
    private final String description;
    @Getter
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
