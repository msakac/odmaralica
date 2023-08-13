package org.foi.diplomski.msakac.odmaralica.security.service;

import org.foi.diplomski.msakac.odmaralica.model.Role;
import org.foi.diplomski.msakac.odmaralica.security.dto.RoleRequestDTO;

public interface RoleService {
    Role save(RoleRequestDTO roleRequest);

    Role find(String name);
}
