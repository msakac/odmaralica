package org.foi.diplomski.msakac.odmaralica.repository;

import org.foi.diplomski.msakac.odmaralica.model.AccommodationUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccommodationUnitRepository extends JpaRepository<AccommodationUnit, Long> {
    AccommodationUnit findByResidenceIdAndName(long residenceId, String name);

    List<AccommodationUnit> findAllByResidenceId(Long id);
}
