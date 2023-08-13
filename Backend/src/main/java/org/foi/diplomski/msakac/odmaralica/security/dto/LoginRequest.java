package org.foi.diplomski.msakac.odmaralica.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {

    @NotEmpty(message = "{login_email_not_empty}")
    private String email;

    @NotEmpty(message = "{login_password_not_empty}")
    private String password;

}
