package com.QRMenu.menu.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {

    @NotEmpty(message = "Password is mandatory")
    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should be 6 characters long minimum")
    private String password;

    public ResetPasswordRequest() {
    }

    @JsonCreator
    public ResetPasswordRequest(@JsonProperty("password") String password) {
        this.password = password;
    }
}
