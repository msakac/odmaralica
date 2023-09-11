package org.foi.diplomski.msakac.odmaralica.service.security.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.security.*;
import org.foi.diplomski.msakac.odmaralica.exceptions.AccountNotActivatedException;
import org.foi.diplomski.msakac.odmaralica.exceptions.InvalidActivationTokenException;
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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenManager jwtTokenManager;
    private final IUserService userService;
    private final IUserTokenService userTokenService;
    private final UserMapper userMapper;
    private final EmailSenderService emailSenderService;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, IUserService userService,
                                     JwtTokenManager jwtTokenManager, IUserTokenService userTokenService, EmailSenderService emailSenderService, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenManager = jwtTokenManager;
        this.userTokenService = userTokenService;
        this.emailSenderService = emailSenderService;
        this.userMapper = userMapper;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        //Authenticate user
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        try{
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (Exception e){
            throw new BadCredentialsException("Bad credentials for email " + loginRequestDTO.getEmail());
        }
        final AuthenticatedUserDTO authenticatedUserDTO = userService.findAuthenticatedUserByEmail(loginRequestDTO.getEmail());

        final User user = userMapper.convertToUser(authenticatedUserDTO);

        //Check if user is activated
        if (!user.getActivated()) {

            UserToken activationToken = new UserToken(user, TokenType.Activation);
            // If there is no valid token, create new one and return true.
            boolean isCreated = userTokenService.deactivateAndCreateActivationToken(activationToken);

            if (isCreated) {
                UserGetDTO userGetDTO = userMapper.convertToUserGetDTO(user);
                emailSenderService.sendActivationEmail(userGetDTO, activationToken);
                throw new AccountNotActivatedException("Account with email '" + user.getEmail()
                        + "' is not activated. New activation token is being sent to your email.");
            }

            throw new AccountNotActivatedException("Account with email '" + user.getEmail()
                    + "' is not activated. Please check your email for activation token.");
        }

        //Create tokens and return them in response
        final String accessToken = jwtTokenManager.generateToken(user);
        final String refreshToken = jwtTokenManager.generateRefreshToken(user);

        return new LoginResponseDTO(accessToken, refreshToken, authenticatedUserDTO);
    }

    @Override
    public LoginResponseDTO loginOpenAuth(String token) {
        Long userId = jwtTokenManager.getUserIdFromToken(token);
        User user = userService.findById(userId);
        AuthenticatedUserDTO authenticatedUserDTO = userMapper.convertToAuthenticatedUserDto(user);
        final String refreshToken = jwtTokenManager.generateRefreshToken(user);

        return new LoginResponseDTO(token, refreshToken, authenticatedUserDTO);
    }


    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        User registeredUser = userService.registration(registerRequestDTO);
        UserToken activationToken = new UserToken(registeredUser, TokenType.Activation);
        UserGetDTO userGetDTO = userMapper.convertToUserGetDTO(registeredUser);
        userTokenService.create(activationToken);
        emailSenderService.sendActivationEmail(userGetDTO, activationToken);
        return new RegisterResponseDTO(userGetDTO);
    }

    public void activateAccount(String token) {

        Timestamp current = new Timestamp(System.currentTimeMillis());
        UserToken activationToken = userTokenService.findByToken(token);

        // Check validity of token
        if (activationToken == null || activationToken.isUsed()
                || activationToken.getType() != TokenType.Activation
                || current.after(activationToken.getExpiresAt())) {

            throw new InvalidActivationTokenException("Invalid activation token.");
        }

        // Activate user
        User user = activationToken.getUser();
        user.setActivated(true);
        userService.updateSecure(user);

        // Set token as used
        activationToken.setUsed(true);
        userTokenService.update(activationToken);

        emailSenderService.sendActivationSuccessEmail(userMapper.convertToUserGetDTO(user));
    }

}
