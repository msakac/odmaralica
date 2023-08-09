package org.foi.diplomski.msakac.odmaralica.repository;

import org.foi.diplomski.msakac.odmaralica.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
