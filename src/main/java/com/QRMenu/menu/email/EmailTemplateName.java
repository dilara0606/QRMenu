package com.QRMenu.menu.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {

    ACTIVATE_ACCOUNT("activate_account"),
    RESET_PASSWORD("reset_password");

    private final String name;

    private EmailTemplateName(final String name) {
        this.name = name;
    }
}
