package org.foi.diplomski.msakac.odmaralica.repository;

import java.util.List;

import org.foi.diplomski.msakac.odmaralica.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    void deleteByAccommodationUnitId(Long id);
    void deleteByResidenceId(Long id);
    void deleteByUserId(Long id);
    List<Image> findAllByAccommodationUnitId(Long id);
    List<Image> findAllByResidenceId(Long id);
    List<Image> findAllByUserId(Long id);
}