package org.foi.diplomski.msakac.odmaralica.service;

import org.foi.diplomski.msakac.odmaralica.model.Residence;

public interface ResidenceService {
    Residence createResidence(Residence residence, Long ownerId);
}
