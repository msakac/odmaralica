package org.foi.diplomski.msakac.odmaralica.repository;

import org.foi.diplomski.msakac.odmaralica.model.AccommodationUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationUnitRepository extends JpaRepository<AccommodationUnit, Long> {
    AccommodationUnit findByResidenceIdAndName(long residenceId, String name);
}
