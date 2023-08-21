package org.foi.diplomski.msakac.odmaralica.controller.security;

import lombok.RequiredArgsConstructor;

import org.foi.diplomski.msakac.odmaralica.dto.security.RegistrationRequest;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegistrationResponse;
import org.foi.diplomski.msakac.odmaralica.service.security.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<RegistrationResponse> registrationRequest(@Valid @RequestBody RegistrationRequest registrationRequest) {

        final RegistrationResponse registrationResponse = userService.registration(registrationRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
    }

}
