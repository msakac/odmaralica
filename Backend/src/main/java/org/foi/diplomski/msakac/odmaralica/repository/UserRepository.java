package org.foi.diplomski.msakac.odmaralica.repository;

import java.util.List;

import org.foi.diplomski.msakac.odmaralica.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {

	// User findByEmail(String email);

	boolean existsByEmail(String email);

	// @Query(value = "SELECT * FROM users WHERE age > :age", nativeQuery = true)
    // List<User> findUsersByAgeGreaterThan(int age);
}
