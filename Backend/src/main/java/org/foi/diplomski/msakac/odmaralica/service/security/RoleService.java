package org.foi.diplomski.msakac.odmaralica.service.security;

import org.foi.diplomski.msakac.odmaralica.dto.security.RoleRequestDTO;
import org.foi.diplomski.msakac.odmaralica.model.Role;

public interface RoleService {
    Role save(RoleRequestDTO roleRequest);

    Role find(String name);
}
