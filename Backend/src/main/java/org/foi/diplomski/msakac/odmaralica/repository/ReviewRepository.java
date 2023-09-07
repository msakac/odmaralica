package org.foi.diplomski.msakac.odmaralica.repository;

import java.util.List;

import org.foi.diplomski.msakac.odmaralica.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByResidenceId(Long residenceId);
    List<Review> findByResidenceIdAndUserId(Long residenceId, Long userId);
}
