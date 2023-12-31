package org.foi.diplomski.msakac.odmaralica.service;

import org.foi.diplomski.msakac.odmaralica.dto.custom.CountryRegionCityGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.CountryPostDTO;
import org.foi.diplomski.msakac.odmaralica.model.Country;

import java.util.List;

public interface ICountryService {
    Country create(CountryPostDTO country);

    Country findByName(String name);

    Country findByCountryCode(String name);

    Country findById(Long id);

    List<Country> findAll();

    Country update(Country country);

    void delete(Long id);

    List<Country> find(String queryParams);

    List<CountryRegionCityGetDTO> findAllWithRegionsAndCities();
}
