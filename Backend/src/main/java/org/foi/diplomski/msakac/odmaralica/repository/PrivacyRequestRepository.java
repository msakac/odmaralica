package org.foi.diplomski.msakac.odmaralica.repository;

import java.util.List;

import org.foi.diplomski.msakac.odmaralica.model.security.PrivacyRequest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PrivacyRequestRepository extends JpaRepository<PrivacyRequest, Long> {
    List<PrivacyRequest> findByUserId(Long userId);
}
