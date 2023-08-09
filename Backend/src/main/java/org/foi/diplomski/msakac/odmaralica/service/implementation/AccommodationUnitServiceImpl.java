package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.model.AccommodationUnit;
import org.foi.diplomski.msakac.odmaralica.repository.AccommodationUnitRepository;
import org.foi.diplomski.msakac.odmaralica.service.AccommodationUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccommodationUnitServiceImpl implements AccommodationUnitService {

    private final AccommodationUnitRepository accommodationUnitRepository;

    @Autowired
    public AccommodationUnitServiceImpl(AccommodationUnitRepository accommodationUnitRepository) {
        this.accommodationUnitRepository = accommodationUnitRepository;
    }

    @Override
    public AccommodationUnit createAccommodationUnit(AccommodationUnit accommodationUnit) {
        return accommodationUnitRepository.save(accommodationUnit);
    }
}
