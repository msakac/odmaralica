package org.foi.diplomski.msakac.odmaralica.controller.security;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.AuthenticatedUserDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.service.security.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    private final CreateResponseDTO<User> notFoundResponse = new CreateResponseDTO<User>(HttpStatus.NOT_FOUND, "User not found");

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long id) {

        // If user is logged in and is requesting his own data return AuthenticatedUserDTO
        AuthenticatedUserDTO authenticatedUserDTO = userService.getAuthenticatedUser(id);
        if (authenticatedUserDTO != null) {
            CreateResponseDTO<AuthenticatedUserDTO> response = new CreateResponseDTO<AuthenticatedUserDTO>(authenticatedUserDTO, HttpStatus.OK);
            return ResponseEntity.ok(response);
        }
        // If user is not logged in or is not requesting his own data return UserGetDTO
        UserGetDTO user = userService.findByIdDTO(id);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }

        CreateResponseDTO<UserGetDTO> response = new CreateResponseDTO<UserGetDTO>(user, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }
}
