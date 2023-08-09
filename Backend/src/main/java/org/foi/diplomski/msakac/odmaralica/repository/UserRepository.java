package org.foi.diplomski.msakac.odmaralica.repository;

import org.foi.diplomski.msakac.odmaralica.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);
}
