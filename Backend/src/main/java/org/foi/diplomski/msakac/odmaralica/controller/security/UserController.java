package org.foi.diplomski.msakac.odmaralica.controller.security;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.AuthenticatedUserDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserPostDTO;
import org.foi.diplomski.msakac.odmaralica.exceptions.InvalidRequestResponseBuilder;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.service.security.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<User> users = userService.findAll();
        CreateResponseDTO<List<User>> response = new CreateResponseDTO<List<User>>(users, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> userPost(@Valid @RequestBody UserPostDTO userRequest) {
        try {
            User createdEntity = userService.createUser(userRequest);
            return ResponseEntity.ok(new CreateResponseDTO<User>(createdEntity, HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(InvalidRequestResponseBuilder.createResponse(e));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            CreateResponseDTO<User> response = new CreateResponseDTO<User>(HttpStatus.NOT_FOUND, "Entity not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        try {
            userService.delete(id);
            return ResponseEntity.ok(new CreateResponseDTO<User>(HttpStatus.OK, "Entity deleted"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(InvalidRequestResponseBuilder.createResponse(e));
        }
    }

    @PutMapping()
    public ResponseEntity<Object> update(@Valid @RequestBody User dto) {
        try {
            User updatedEntity = userService.update(dto);
            return ResponseEntity.ok(new CreateResponseDTO<User>(updatedEntity, HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(InvalidRequestResponseBuilder.createResponse(e));
        }
    }


    //Za put saljem cijeli objekt, doputam mijenjanje lozinke, ako je lozinka identicna ko u bazi onda lozinku ne diraj inace kriptiraj i zamijeni
}
