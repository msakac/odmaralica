package org.foi.diplomski.msakac.odmaralica.dto.security;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class RefreshTokenRequestDTO {
    @NotEmpty(message = "Token cannot be empty")
    private String refreshToken;
}
