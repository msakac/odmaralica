package org.foi.diplomski.msakac.odmaralica.service.security;

import org.foi.diplomski.msakac.odmaralica.dto.security.AuthenticatedUserDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegisterRequestDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegisterResponseDTO;


public interface IUserService {

    RegisterResponseDTO registration(RegisterRequestDTO registrationRequest);

    AuthenticatedUserDTO findAuthenticatedUserByEmail(String email);

}
