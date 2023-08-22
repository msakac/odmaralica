package org.foi.diplomski.msakac.odmaralica.service.security;

import org.foi.diplomski.msakac.odmaralica.dto.security.AuthenticatedUserDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegisterRequestDTO;
import org.foi.diplomski.msakac.odmaralica.model.User;


public interface IUserService {

    User registration(RegisterRequestDTO registrationRequest);

    AuthenticatedUserDTO findAuthenticatedUserByEmail(String email);

    User update(User user);

}
