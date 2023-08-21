package org.foi.diplomski.msakac.odmaralica.service.security;

import org.foi.diplomski.msakac.odmaralica.dto.security.AuthenticatedUserDto;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegistrationRequest;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegistrationResponse;


public interface UserService {

    RegistrationResponse registration(RegistrationRequest registrationRequest);

    AuthenticatedUserDto findAuthenticatedUserByEmail(String email);

}
