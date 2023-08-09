package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.model.ResidenceAttribute;
import org.foi.diplomski.msakac.odmaralica.service.ResidenceAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/residence-attributes")
public class ResidenceAttributeController {

    private final ResidenceAttributeService residenceAttributeService;

    @Autowired
    public ResidenceAttributeController(ResidenceAttributeService residenceAttributeService) {
        this.residenceAttributeService = residenceAttributeService;
    }

    @PostMapping
    public ResponseEntity<ResidenceAttribute> createResidenceAttribute(@RequestBody ResidenceAttribute residenceAttribute) {
        ResidenceAttribute createdResidenceAttribute = residenceAttributeService.createResidenceAttribute(residenceAttribute);
        return ResponseEntity.ok(createdResidenceAttribute);
    }
}
