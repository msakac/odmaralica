package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.model.Amount;
import org.foi.diplomski.msakac.odmaralica.repository.AmountRepository;
import org.foi.diplomski.msakac.odmaralica.service.AmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmountServiceImpl implements AmountService {

    private final AmountRepository amountRepository;

    @Autowired
    public AmountServiceImpl(AmountRepository amountRepository) {
        this.amountRepository = amountRepository;
    }

    @Override
    public Amount createAmount(Amount amount) {
        return amountRepository.save(amount);
    }
}
