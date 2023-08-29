package org.foi.diplomski.msakac.odmaralica.repository;

import java.util.List;

import org.foi.diplomski.msakac.odmaralica.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    // City findByName(String name);

    // City findByNameAndZip(String name, String zip);

    List<City> findAllByRegionId(Long id);
}
