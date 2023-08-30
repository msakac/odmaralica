package org.foi.diplomski.msakac.odmaralica.service.security;

import java.util.List;

import org.foi.diplomski.msakac.odmaralica.dto.security.RoleRequestDTO;
import org.foi.diplomski.msakac.odmaralica.model.Role;

public interface IRoleService {
    Role save(RoleRequestDTO roleRequest);
    Role findById(Long id);
    Role find(String name);
    List<Role> findAll();
}
