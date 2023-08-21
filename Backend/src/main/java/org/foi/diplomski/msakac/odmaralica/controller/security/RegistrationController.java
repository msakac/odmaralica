package org.foi.diplomski.msakac.odmaralica.controller.security;

import lombok.RequiredArgsConstructor;

import org.foi.diplomski.msakac.odmaralica.dto.security.RegisterRequestDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegisterResponseDTO;
import org.foi.diplomski.msakac.odmaralica.service.security.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<RegisterResponseDTO> registrationRequest(@Valid @RequestBody RegisterRequestDTO registrationRequest) {

        final RegisterResponseDTO registrationResponse = userService.registration(registrationRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
    }

}
