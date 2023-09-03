package org.foi.diplomski.msakac.odmaralica.repository;

import java.util.List;

import org.foi.diplomski.msakac.odmaralica.model.AccommodationUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationUnitRepository extends JpaRepository<AccommodationUnit, Long> {
    AccommodationUnit findByResidenceIdAndName(long residenceId, String name);
    List<AccommodationUnit> findAllByResidenceId(Long id);
}
