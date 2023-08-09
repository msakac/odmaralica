package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.model.ResidenceAttribute;
import org.foi.diplomski.msakac.odmaralica.repository.ResidenceAttributeRepository;
import org.foi.diplomski.msakac.odmaralica.service.ResidenceAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenceAttributeServiceImpl implements ResidenceAttributeService {

    private final ResidenceAttributeRepository residenceAttributeRepository;

    @Autowired
    public ResidenceAttributeServiceImpl(ResidenceAttributeRepository residenceAttributeRepository) {
        this.residenceAttributeRepository = residenceAttributeRepository;
    }

    @Override
    public ResidenceAttribute createResidenceAttribute(ResidenceAttribute residenceAttribute) {
        return residenceAttributeRepository.save(residenceAttribute);
    }
}
