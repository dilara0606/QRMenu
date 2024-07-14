package com.QRMenu.menu.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResetPasswordRequest {

    @NotEmpty
    private String token;

    @NotEmpty
    private String newPassword;
}
