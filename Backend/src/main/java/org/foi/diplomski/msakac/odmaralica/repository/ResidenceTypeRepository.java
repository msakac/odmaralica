package org.foi.diplomski.msakac.odmaralica.repository;

import org.foi.diplomski.msakac.odmaralica.model.ResidenceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidenceTypeRepository extends JpaRepository<ResidenceType, Long> {
    ResidenceType findByName(String name);
}
