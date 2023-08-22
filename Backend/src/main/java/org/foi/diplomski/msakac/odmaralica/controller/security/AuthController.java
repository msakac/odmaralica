package org.foi.diplomski.msakac.odmaralica.controller.security;

import javax.validation.Valid;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.LoginRequestDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.LoginResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegisterRequestDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegisterResponseDTO;
import org.foi.diplomski.msakac.odmaralica.exceptions.EmailAlreadyExistException;
import org.foi.diplomski.msakac.odmaralica.exceptions.InvalidRequestResponseBuilder;
import org.foi.diplomski.msakac.odmaralica.service.security.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            CreateResponseDTO<LoginResponseDTO> loginResponse = new CreateResponseDTO<LoginResponseDTO>(response, HttpStatus.OK);
            return ResponseEntity.ok(loginResponse);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(InvalidRequestResponseBuilder.createResponse(e));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequestDTO registerRequest) {
        try{
            RegisterResponseDTO response = authService.register(registerRequest);
            CreateResponseDTO<RegisterResponseDTO> regResponse = new CreateResponseDTO<RegisterResponseDTO>(response, HttpStatus.OK);
            return ResponseEntity.ok(regResponse);
        } catch(Exception e){
            if(e instanceof EmailAlreadyExistException){
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


}
