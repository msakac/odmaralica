package org.foi.diplomski.msakac.odmaralica.security.service;

import org.foi.diplomski.msakac.odmaralica.security.dto.AuthenticatedUserDto;
import org.foi.diplomski.msakac.odmaralica.security.dto.RegistrationRequest;
import org.foi.diplomski.msakac.odmaralica.security.dto.RegistrationResponse;


public interface UserService {

	RegistrationResponse registration(RegistrationRequest registrationRequest);

	AuthenticatedUserDto findAuthenticatedUserByEmail(String email);

}
