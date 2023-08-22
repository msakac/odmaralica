package org.foi.diplomski.msakac.odmaralica.service.security.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.security.AuthenticatedUserDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.LoginRequestDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.LoginResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegisterRequestDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegisterResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.security.UserMapper;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.model.security.TokenType;
import org.foi.diplomski.msakac.odmaralica.model.security.UserToken;
import org.foi.diplomski.msakac.odmaralica.security.jwt.JwtTokenManager;
import org.foi.diplomski.msakac.odmaralica.service.implementation.EmailSenderService;
import org.foi.diplomski.msakac.odmaralica.service.security.IAuthenticationService;
import org.foi.diplomski.msakac.odmaralica.service.security.IUserService;
import org.foi.diplomski.msakac.odmaralica.service.security.IUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenManager jwtTokenManager;
    private final IUserService userService;
    private final IUserTokenService userTokenService;
    private final EmailSenderService emailSenderService;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, IUserService userService, 
            JwtTokenManager jwtTokenManager, IUserTokenService userTokenService, EmailSenderService emailSenderService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenManager = jwtTokenManager;
        this.userTokenService = userTokenService;
        this.emailSenderService = emailSenderService;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        final AuthenticatedUserDTO authenticatedUserDTO = userService.findAuthenticatedUserByEmail(loginRequestDTO.getEmail());

        final User user = UserMapper.INSTANCE.convertToUser(authenticatedUserDTO);
        final String accessToken = jwtTokenManager.generateToken(user);
        final String refreshToken = jwtTokenManager.generateRefreshToken(user);

        return new LoginResponseDTO(accessToken, refreshToken);
    }

    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        User registeredUser = userService.registration(registerRequestDTO);
        UserToken activationToken = new UserToken(registeredUser, TokenType.PasswordReset);
        UserGetDTO userGetDTO = UserMapper.INSTANCE.convertToUserGetDTO(registeredUser);
        userTokenService.create(activationToken);
        emailSenderService.sendActivationEmail(userGetDTO, activationToken);
        return new RegisterResponseDTO(userGetDTO);
    }

}
