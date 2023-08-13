package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.model.AccommodationUnit;
import org.foi.diplomski.msakac.odmaralica.service.AccommodationUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accommodation-units")
public class AccommodationUnitController {

    private final AccommodationUnitService accommodationUnitService;

    @Autowired
    public AccommodationUnitController(AccommodationUnitService accommodationUnitService) {
        this.accommodationUnitService = accommodationUnitService;
    }

    @PostMapping
    public ResponseEntity<AccommodationUnit> createAccommodationUnit(@RequestBody AccommodationUnit accommodationUnit) {
        AccommodationUnit createdAccommodationUnit = accommodationUnitService.createAccommodationUnit(accommodationUnit);
        return ResponseEntity.ok(createdAccommodationUnit);
    }
}
