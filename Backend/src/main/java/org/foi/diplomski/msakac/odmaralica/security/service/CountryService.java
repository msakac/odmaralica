package org.foi.diplomski.msakac.odmaralica.security.service;
import org.foi.diplomski.msakac.odmaralica.model.Country;
import org.foi.diplomski.msakac.odmaralica.security.dto.CountryRequestDTO;

public interface CountryService {
    Country save(CountryRequestDTO countryRequest);
    Country find(String name);
}
