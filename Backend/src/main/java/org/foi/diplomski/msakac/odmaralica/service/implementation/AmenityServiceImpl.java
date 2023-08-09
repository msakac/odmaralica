package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.model.Amenity;
import org.foi.diplomski.msakac.odmaralica.repository.AmenityRepository;
import org.foi.diplomski.msakac.odmaralica.service.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmenityServiceImpl implements AmenityService {

    private final AmenityRepository amenityRepository;

    @Autowired
    public AmenityServiceImpl(AmenityRepository amenityRepository) {
        this.amenityRepository = amenityRepository;
    }

    @Override
    public Amenity createAmenity(Amenity amenity) {
        return amenityRepository.save(amenity);
    }
}
