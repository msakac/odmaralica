package org.foi.diplomski.msakac.odmaralica.controller.security;

import javax.validation.Valid;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.LoginRequestDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.LoginResponseDTO;
import org.foi.diplomski.msakac.odmaralica.exceptions.InvalidRequestResponseBuilder;
import org.foi.diplomski.msakac.odmaralica.service.security.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IAuthenticationService authService;

    @Autowired
    public AuthController(IAuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        try {
            LoginResponseDTO response = authService.login(loginRequest);
            CreateResponseDTO<LoginResponseDTO> createResponse = new CreateResponseDTO<LoginResponseDTO>(response, HttpStatus.OK);
            return ResponseEntity.ok(createResponse);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(InvalidRequestResponseBuilder.createResponse(e));
        }
    }

    // @PostMapping("/register")
    // public ResponseEntity<String> register(@RequestBody RegisterRequestDTO registrationRequest) {
    //     // Call authentication service to process user registration
    //     authService.register(registrationRequest);
    //     return ResponseEntity.ok("User registered successfully.");
    // }

    // @PostMapping("/refresh")
    // public ResponseEntity<AuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
    //     // Call authentication service to refresh token
    //     AuthenticationResponse response = authService.refreshToken(refreshTokenRequest);
    //     return ResponseEntity.ok(response);
    // }

    // // Other endpoints for logout, password reset, etc.

}
