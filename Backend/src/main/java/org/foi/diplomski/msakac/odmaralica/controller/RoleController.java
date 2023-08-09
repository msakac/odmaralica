package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.model.Role;
import org.foi.diplomski.msakac.odmaralica.security.dto.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.security.dto.RoleRequestDTO;
import org.foi.diplomski.msakac.odmaralica.security.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<CreateResponseDTO<Role>> rolePost(@Valid @RequestBody RoleRequestDTO roleRequest) {
        Role existingRole = roleService.find(roleRequest.getRole());
        if(existingRole != null) {
            CreateResponseDTO<Role> response = new CreateResponseDTO<Role>(null, HttpStatus.CONFLICT);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        final Role roleResponse = roleService.save(roleRequest);
        CreateResponseDTO<Role> response = new CreateResponseDTO<Role>(roleResponse, HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

