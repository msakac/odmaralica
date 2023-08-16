package org.foi.diplomski.msakac.odmaralica.repository;

import java.util.List;

import org.foi.diplomski.msakac.odmaralica.model.PricePeriod;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PricePeriodRepository extends JpaRepository<PricePeriod, Long> {
    List<PricePeriod> findByAccommodationUnitId(Long accommodationUnitId);
}
