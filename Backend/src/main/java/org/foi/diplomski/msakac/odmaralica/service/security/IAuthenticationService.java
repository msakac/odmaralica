package org.foi.diplomski.msakac.odmaralica.service.security;

import org.foi.diplomski.msakac.odmaralica.dto.security.LoginRequestDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.LoginResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegisterRequestDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegisterResponseDTO;

public interface IAuthenticationService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    LoginResponseDTO loginOpenAuth(String token);

    RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO);

    void activateAccount(String token);
}
