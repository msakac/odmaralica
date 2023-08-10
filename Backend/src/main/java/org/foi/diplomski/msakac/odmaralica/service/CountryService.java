package org.foi.diplomski.msakac.odmaralica.service;

import java.util.List;

import org.foi.diplomski.msakac.odmaralica.dto.post.CountryPostDTO;
import org.foi.diplomski.msakac.odmaralica.model.Country;

public interface CountryService {
    Country create(CountryPostDTO country);
    Country findByName(String name);
    Country findByCountryCode(String name);
    Country findById(Long id);
    List<Country> findAll();
    Country update(Country country);
    void delete(Long id);
    List<Country> find(String queryParams);
}
