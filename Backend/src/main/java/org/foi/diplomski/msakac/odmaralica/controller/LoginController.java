package org.foi.diplomski.msakac.odmaralica.controller;

import lombok.RequiredArgsConstructor;
import org.foi.diplomski.msakac.odmaralica.security.dto.LoginRequest;
import org.foi.diplomski.msakac.odmaralica.security.dto.LoginResponse;
import org.foi.diplomski.msakac.odmaralica.security.jwt.JwtTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final JwtTokenService jwtTokenService;

    @PostMapping
    public ResponseEntity<LoginResponse> loginRequest(@Valid @RequestBody LoginRequest loginRequest) {

        final LoginResponse loginResponse = jwtTokenService.getLoginResponse(loginRequest);

        return ResponseEntity.ok(loginResponse);
    }

}
