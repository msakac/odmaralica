package org.foi.diplomski.msakac.odmaralica.service;

import org.foi.diplomski.msakac.odmaralica.model.Country;

public interface CountryService {
    Country create(Country country);
    Country find(String name);
}
