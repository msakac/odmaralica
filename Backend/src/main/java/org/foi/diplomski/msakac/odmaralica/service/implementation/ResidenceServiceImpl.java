package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.model.Residence;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.repository.ResidenceRepository;
import org.foi.diplomski.msakac.odmaralica.repository.UserRepository;
import org.foi.diplomski.msakac.odmaralica.service.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenceServiceImpl implements ResidenceService {

    private final ResidenceRepository residenceRepository;
    private final UserRepository userRepository;

    @Autowired
    public ResidenceServiceImpl(ResidenceRepository residenceRepository, UserRepository userRepository) {
        this.residenceRepository = residenceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Residence createResidence(Residence residence, Long ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Owner with ID " + ownerId + " not found"));

        residence.setOwner(owner);
        return residenceRepository.save(residence);
    }
}
