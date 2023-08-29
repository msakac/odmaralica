package org.foi.diplomski.msakac.odmaralica.controller;

import lombok.RequiredArgsConstructor;
import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.custom.CountryRegionCityGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.CountryPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.CountryPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Country;
import org.foi.diplomski.msakac.odmaralica.service.ICountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/country")
public class CountryController {
    private final ICountryService countryService;
    private final CreateResponseDTO<Country> notFoundResponse = new CreateResponseDTO<Country>
            (HttpStatus.NOT_FOUND, "Country not found");
    private final CreateResponseDTO<Country> conflictResponse = new CreateResponseDTO<Country>
            (HttpStatus.CONFLICT, "Name or Country Code already exists!");

    @PostMapping
    public ResponseEntity<Object> countryPost(@Valid @RequestBody CountryPostDTO countryRequest) {
        Country existingName = countryService.findByName(countryRequest.getName());
        Country existingCountry = countryService.findByCountryCode(countryRequest.getCountryCode());

        if (existingName != null || existingCountry != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(conflictResponse);
        }

        final Country countryResponse = countryService.create(countryRequest);
        CreateResponseDTO<Country> response = new CreateResponseDTO<Country>(countryResponse, HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCountryById(@PathVariable Long id) {
        Country country = countryService.findById(id);

        if (country == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }

        CreateResponseDTO<Country> response = new CreateResponseDTO<Country>(country, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Object> getAllCountries() {
        List<Country> countries = countryService.findAll();
        CreateResponseDTO<List<Country>> response = new CreateResponseDTO<List<Country>>(countries, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/with-regions-and-cities")
    public ResponseEntity<Object> getAllCountriesWithRegionsAndCities() {
        List<CountryRegionCityGetDTO> countries = countryService.findAllWithRegionsAndCities();
        CreateResponseDTO<List<CountryRegionCityGetDTO>> response = new CreateResponseDTO<List<CountryRegionCityGetDTO>>(countries, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCountry(@PathVariable Long id,
                                                @Valid @RequestBody CountryPutDTO countryRequest) {
        Country existingCountry = countryService.findById(id);

        if (existingCountry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }

        Country existingName = countryService.findByName(countryRequest.getName());
        Country existingCountryCode = countryService.findByCountryCode(countryRequest.getCountryCode());

        if (existingName != null && existingName.getId() != existingCountry.getId()
                || existingCountryCode != null && existingCountryCode.getId() != existingCountry.getId()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(conflictResponse);
        }

        existingCountry.setName(countryRequest.getName());
        existingCountry.setCountryCode(countryRequest.getCountryCode());

        final Country countryResponse = countryService.update(existingCountry);
        CreateResponseDTO<Country> response = new CreateResponseDTO<Country>(countryResponse, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCountry(@PathVariable Long id) {
        Country existingCountry = countryService.findById(id);

        if (existingCountry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }

        countryService.delete(id);
        CreateResponseDTO<Country> response = new CreateResponseDTO<Country>(HttpStatus.OK, "Country deleted");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find")
    public ResponseEntity<Object> queryCountries(@RequestParam("q") String queryParams) {
        // FIXME: Osim q parametara trebam jos sort=, offset, limit
        List<Country> countries = countryService.find(queryParams);

        if (countries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }

        CreateResponseDTO<List<Country>> response = new CreateResponseDTO<List<Country>>(countries, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

}

