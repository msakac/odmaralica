package org.foi.diplomski.msakac.odmaralica.repository;

import org.foi.diplomski.msakac.odmaralica.model.security.PrivacyRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PrivacyRequestRepository extends JpaRepository<PrivacyRequest, Long> {
    List<PrivacyRequest> findByUserId(Long userId);
}
