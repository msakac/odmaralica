package org.foi.diplomski.msakac.odmaralica.controller.security;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.*;
import org.foi.diplomski.msakac.odmaralica.exceptions.EmailAlreadyExistException;
import org.foi.diplomski.msakac.odmaralica.exceptions.InvalidRequestResponseBuilder;
import org.foi.diplomski.msakac.odmaralica.service.security.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
            CreateResponseDTO<LoginResponseDTO> loginResponse = new CreateResponseDTO<LoginResponseDTO>(response, HttpStatus.OK);
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(InvalidRequestResponseBuilder.createResponse(e));
        }
    }

    @PostMapping("/login-open-auth")
    public ResponseEntity<Object> loginOpenAuth(@Valid @RequestBody LoginOpenAuthRequestDTO request) {
        try {
            LoginResponseDTO response = authService.loginOpenAuth(request.getToken());
            CreateResponseDTO<LoginResponseDTO> loginResponse = new CreateResponseDTO<LoginResponseDTO>(response, HttpStatus.OK);
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(InvalidRequestResponseBuilder.createResponse(e));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequestDTO registerRequest) {
        try {
            RegisterResponseDTO response = authService.register(registerRequest);
            CreateResponseDTO<RegisterResponseDTO> regResponse = new CreateResponseDTO<RegisterResponseDTO>(response, HttpStatus.OK);
            return ResponseEntity.ok(regResponse);
        } catch (Exception e) {
            if (e instanceof EmailAlreadyExistException) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(InvalidRequestResponseBuilder.createResponse(e));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(InvalidRequestResponseBuilder.createResponse(e));
        }
    }

    @PostMapping("/activate")
    public ResponseEntity<Object> activateAccount(@Valid @RequestParam String token) {
        try {
            authService.activateAccount(token);
            CreateResponseDTO<String> activationResponse = new CreateResponseDTO<String>(HttpStatus.OK, "Account activated successfully. You can now login.");
            return ResponseEntity.ok(activationResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(InvalidRequestResponseBuilder.createResponse(e));
        }
    }

    //TODO dodati /log-out tako da morem deaktivirati refresh token

    //TODO dodati /reset-password da mogu resetirati lozinku korisnika

    //TODO dodati /forgot-password da mogu poslati korisniku link za resetiranje lozinke

    //TODO dodati /refresh-token da mogu refreshati access token


}
