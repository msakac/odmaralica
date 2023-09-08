package org.foi.diplomski.msakac.odmaralica.dto.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@NoArgsConstructor
public class LoginOpenAuthRequestDTO {

    @NotEmpty(message = "Token cannot be empty")
    private String token;
}
