package org.foi.diplomski.msakac.odmaralica.controller;

import javax.servlet.http.HttpServletRequest;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.LoginResponseDTO;
import org.foi.diplomski.msakac.odmaralica.utils.SecurityConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.User;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<Object> sayHello(HttpServletRequest request) {
        String token = extractToken(request.getHeader("Authorization"));
        String username = SecurityConstants.getAuthenticatedUsername();
        CreateResponseDTO<String> createResponse = new CreateResponseDTO<String>("bok" + username + token, HttpStatus.OK);
        return ResponseEntity.ok(createResponse);
    }

    private String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

}
