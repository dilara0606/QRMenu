package com.QRMenu.menu.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordRequest {

    @Email
    @NotEmpty(message = "Email is mandatory")
    private String email;

    public ForgotPasswordRequest() {

    }

    @JsonCreator
    public ForgotPasswordRequest(@JsonProperty("email") String email) {
        this.email = email;
    }
}
