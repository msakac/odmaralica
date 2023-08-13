package org.foi.diplomski.msakac.odmaralica.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.security.dto.AuthenticatedUserDto;
import org.foi.diplomski.msakac.odmaralica.security.dto.LoginRequest;
import org.foi.diplomski.msakac.odmaralica.security.dto.LoginResponse;
import org.foi.diplomski.msakac.odmaralica.security.mapper.UserMapper;
import org.foi.diplomski.msakac.odmaralica.security.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private final UserService userService;

    private final JwtTokenManager jwtTokenManager;

    private final AuthenticationManager authenticationManager;

    public LoginResponse getLoginResponse(LoginRequest loginRequest) {

        final String email = loginRequest.getEmail();
        final String password = loginRequest.getPassword();

        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        final AuthenticatedUserDto authenticatedUserDto = userService.findAuthenticatedUserByEmail(email);

        final User user = UserMapper.INSTANCE.convertToUser(authenticatedUserDto);
        final String token = jwtTokenManager.generateToken(user);

        log.info("{} has successfully logged in!", user.getEmail());

        return new LoginResponse(token);
    }

}
