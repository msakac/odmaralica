package org.foi.diplomski.msakac.odmaralica.repository;

import org.foi.diplomski.msakac.odmaralica.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

	// User findByEmail(String email);

	boolean existsByEmail(String email);

	// @Query(value = "SELECT * FROM users WHERE age > :age", nativeQuery = true)
    // List<User> findUsersByAgeGreaterThan(int age);
}
