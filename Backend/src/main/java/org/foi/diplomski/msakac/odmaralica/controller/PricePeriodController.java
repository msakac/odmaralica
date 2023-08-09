package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.model.PricePeriod;
import org.foi.diplomski.msakac.odmaralica.service.PricePeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/price-periods")
public class PricePeriodController {

    private final PricePeriodService pricePeriodService;

    @Autowired
    public PricePeriodController(PricePeriodService pricePeriodService) {
        this.pricePeriodService = pricePeriodService;
    }

    @PostMapping
    public ResponseEntity<PricePeriod> createPricePeriod(@RequestBody PricePeriod pricePeriod) {
        PricePeriod createdPricePeriod = pricePeriodService.createPricePeriod(pricePeriod);
        return ResponseEntity.ok(createdPricePeriod);
    }
}
