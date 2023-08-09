package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.model.ResidenceImage;
import org.foi.diplomski.msakac.odmaralica.repository.ResidenceImageRepository;
import org.foi.diplomski.msakac.odmaralica.service.ResidenceImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenceImageServiceImpl implements ResidenceImageService {

    private final ResidenceImageRepository residenceImageRepository;

    @Autowired
    public ResidenceImageServiceImpl(ResidenceImageRepository residenceImageRepository) {
        this.residenceImageRepository = residenceImageRepository;
    }

    @Override
    public ResidenceImage createResidenceImage(ResidenceImage residenceImage) {
        return residenceImageRepository.save(residenceImage);
    }
}
