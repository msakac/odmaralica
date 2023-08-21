package org.foi.diplomski.msakac.odmaralica.dto.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@AllArgsConstructor
public class LoginRequestDTO {

    @NotEmpty(message = "{login_email_not_empty}")
    private String email;

    @NotEmpty(message = "{login_password_not_empty}")
    private String password;

}
