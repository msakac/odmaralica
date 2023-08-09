package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.model.PricePeriod;
import org.foi.diplomski.msakac.odmaralica.repository.PricePeriodRepository;
import org.foi.diplomski.msakac.odmaralica.service.PricePeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PricePeriodServiceImpl implements PricePeriodService {

    private final PricePeriodRepository pricePeriodRepository;

    @Autowired
    public PricePeriodServiceImpl(PricePeriodRepository pricePeriodRepository) {
        this.pricePeriodRepository = pricePeriodRepository;
    }

    @Override
    public PricePeriod createPricePeriod(PricePeriod pricePeriod) {
        return pricePeriodRepository.save(pricePeriod);
    }
}
