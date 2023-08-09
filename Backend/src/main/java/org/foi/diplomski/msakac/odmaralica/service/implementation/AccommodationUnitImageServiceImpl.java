package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.model.AccommodationUnitImage;
import org.foi.diplomski.msakac.odmaralica.repository.AccommodationUnitImageRepository;
import org.foi.diplomski.msakac.odmaralica.service.AccommodationUnitImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccommodationUnitImageServiceImpl implements AccommodationUnitImageService {

    private final AccommodationUnitImageRepository accommodationUnitImageRepository;

    @Autowired
    public AccommodationUnitImageServiceImpl(AccommodationUnitImageRepository accommodationUnitImageRepository) {
        this.accommodationUnitImageRepository = accommodationUnitImageRepository;
    }

    @Override
    public AccommodationUnitImage createAccommodationUnitImage(AccommodationUnitImage accommodationUnitImage) {
        return accommodationUnitImageRepository.save(accommodationUnitImage);
    }
}
