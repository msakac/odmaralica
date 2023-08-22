package org.foi.diplomski.msakac.odmaralica.repository;


import org.foi.diplomski.msakac.odmaralica.model.security.TokenType;
import org.foi.diplomski.msakac.odmaralica.model.security.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    UserToken findByUserIdAndTypeAndIsUsedFalse(Long userId, TokenType type);
}
