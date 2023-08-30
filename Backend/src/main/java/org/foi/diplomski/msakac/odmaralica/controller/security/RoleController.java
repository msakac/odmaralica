package org.foi.diplomski.msakac.odmaralica.controller.security;

import lombok.RequiredArgsConstructor;
import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.RoleRequestDTO;
import org.foi.diplomski.msakac.odmaralica.model.Role;
import org.foi.diplomski.msakac.odmaralica.service.security.IRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final IRoleService roleService;

    @PostMapping
    public ResponseEntity<CreateResponseDTO<Role>> rolePost(@Valid @RequestBody RoleRequestDTO roleRequest) {
        Role existingRole = roleService.find(roleRequest.getRole());
        if (existingRole != null) {
            CreateResponseDTO<Role> response = new CreateResponseDTO<Role>(null, HttpStatus.CONFLICT);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        final Role roleResponse = roleService.save(roleRequest);
        CreateResponseDTO<Role> response = new CreateResponseDTO<Role>(roleResponse, HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<Object> roleGet() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(new CreateResponseDTO<List<Role>>(roles, HttpStatus.OK));
    }
}

