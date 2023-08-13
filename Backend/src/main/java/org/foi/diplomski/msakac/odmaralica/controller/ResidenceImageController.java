package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.model.ResidenceImage;
import org.foi.diplomski.msakac.odmaralica.service.ResidenceImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/residence-images")
public class ResidenceImageController {

    private final ResidenceImageService residenceImageService;

    @Autowired
    public ResidenceImageController(ResidenceImageService residenceImageService) {
        this.residenceImageService = residenceImageService;
    }

    @PostMapping
    public ResponseEntity<ResidenceImage> createResidenceImage(@RequestBody ResidenceImage residenceImage) {
        ResidenceImage createdResidenceImage = residenceImageService.createResidenceImage(residenceImage);
        return ResponseEntity.ok(createdResidenceImage);
    }
}
