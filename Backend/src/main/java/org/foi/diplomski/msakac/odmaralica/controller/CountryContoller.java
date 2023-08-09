package org.foi.diplomski.msakac.odmaralica.controller;

import lombok.RequiredArgsConstructor;
import org.foi.diplomski.msakac.odmaralica.model.Country;
import org.foi.diplomski.msakac.odmaralica.security.dto.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.service.CountryService;
import org.foi.diplomski.msakac.odmaralica.security.dto.CountryRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/country")
public class CountryContoller {
    private final CountryService countryService;

    @PostMapping
    public ResponseEntity<CreateResponseDTO<Country>> countryPost(@Valid @RequestBody CountryRequestDTO countryRequest) {
        Country existingCountry = countryService.find(countryRequest.getName());
        if(existingCountry != null) {
            CreateResponseDTO<Country> response = new CreateResponseDTO<Country>(null, HttpStatus.CONFLICT);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        final Country countryResponse = countryService.create(existingCountry);
        CreateResponseDTO<Country> response = new CreateResponseDTO<Country>(countryResponse, HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

