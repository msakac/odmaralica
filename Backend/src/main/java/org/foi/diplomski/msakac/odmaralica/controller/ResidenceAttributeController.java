package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.model.ResidenceAttribute;
import org.foi.diplomski.msakac.odmaralica.service.IResidenceAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/residence-attributes")
public class ResidenceAttributeController {

    private final IResidenceAttributeService residenceAttributeService;

    @Autowired
    public ResidenceAttributeController(IResidenceAttributeService residenceAttributeService) {
        this.residenceAttributeService = residenceAttributeService;
    }

    @PostMapping
    public ResponseEntity<ResidenceAttribute> createResidenceAttribute(@RequestBody ResidenceAttribute residenceAttribute) {
        ResidenceAttribute createdResidenceAttribute = residenceAttributeService.createResidenceAttribute(residenceAttribute);
        return ResponseEntity.ok(createdResidenceAttribute);
    }
}
