package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.model.AccommodationUnitImage;
import org.foi.diplomski.msakac.odmaralica.service.IAccommodationUnitImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accommodation-unit-images")
public class AccommodationUnitImageController {

    private final IAccommodationUnitImageService accommodationUnitImageService;

    @Autowired
    public AccommodationUnitImageController(IAccommodationUnitImageService accommodationUnitImageService) {
        this.accommodationUnitImageService = accommodationUnitImageService;
    }

    @PostMapping
    public ResponseEntity<AccommodationUnitImage> createAccommodationUnitImage(
            @RequestBody AccommodationUnitImage accommodationUnitImage) {
        AccommodationUnitImage createdAccommodationUnitImage =
                accommodationUnitImageService.createAccommodationUnitImage(accommodationUnitImage);
        return ResponseEntity.ok(createdAccommodationUnitImage);
    }
}
