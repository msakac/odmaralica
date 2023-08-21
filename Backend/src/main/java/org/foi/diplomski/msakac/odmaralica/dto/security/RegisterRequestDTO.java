package org.foi.diplomski.msakac.odmaralica.dto.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class RegisterRequestDTO {

    @NotEmpty(message = "{registration_name_not_empty}")
    private String name;

    @NotEmpty(message = "{registration_surname_not_empty}")
    private String surname;

    @Email(message = "{registration_email_is_not_valid}")
    @NotEmpty(message = "{registration_email_not_empty}")
    private String email;

    @NotEmpty(message = "{registration_password_not_empty}")
    private String password;


}
