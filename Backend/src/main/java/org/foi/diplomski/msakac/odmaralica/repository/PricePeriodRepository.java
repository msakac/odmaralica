package org.foi.diplomski.msakac.odmaralica.repository;

import org.foi.diplomski.msakac.odmaralica.model.PricePeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PricePeriodRepository extends JpaRepository<PricePeriod, Long> {
    List<PricePeriod> findByAccommodationUnitId(Long accommodationUnitId);
}
