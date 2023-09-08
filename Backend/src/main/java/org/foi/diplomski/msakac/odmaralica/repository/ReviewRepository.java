package org.foi.diplomski.msakac.odmaralica.repository;

import org.foi.diplomski.msakac.odmaralica.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByResidenceId(Long residenceId);

    List<Review> findByResidenceIdAndUserId(Long residenceId, Long userId);
}
