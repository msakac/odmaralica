package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.model.Amount;
import org.foi.diplomski.msakac.odmaralica.service.AmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/amounts")
public class AmountController {

    private final AmountService amountService;

    @Autowired
    public AmountController(AmountService amountService) {
        this.amountService = amountService;
    }

    @PostMapping
    public ResponseEntity<Amount> createAmount(@RequestBody Amount amount) {
        Amount createdAmount = amountService.createAmount(amount);
        return ResponseEntity.ok(createdAmount);
    }
}
