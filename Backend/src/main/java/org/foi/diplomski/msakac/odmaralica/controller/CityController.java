package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.CityPostDTO;
import org.foi.diplomski.msakac.odmaralica.model.City;
import org.foi.diplomski.msakac.odmaralica.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    // private final CityService cityService;

    // @Autowired
    // public CityController(CityService cityService) {
    //     this.cityService = cityService;
    // }

    // @PostMapping
    // public ResponseEntity<Object> createCity(@Valid @RequestBody CityPostDTO cityPostDTO) {
    //     City existingCity = cityService.findByNameAndZip(cityPostDTO.getName(), cityPostDTO.getZip());

    //     if (existingCity != null) {
    //         CreateResponseDTO<City> conflictResponse = new CreateResponseDTO<City>
    //                 (HttpStatus.CONFLICT, "City with the same name and zip code already exists!");
    //         return ResponseEntity.status(HttpStatus.CONFLICT).body(conflictResponse);
    //     }

    //     City createdCity = cityService.createCity(cityPostDTO);
    //     CreateResponseDTO<City> response = new CreateResponseDTO<City>(createdCity, HttpStatus.OK);
    //     return ResponseEntity.ok(response);
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<Object> getCityById(@PathVariable Long id) {
    //     City city = cityService.findCityById(id);

    //     if (city == null) {
    //         CreateResponseDTO<City> notFoundResponse = new CreateResponseDTO<City>
    //                 (HttpStatus.NOT_FOUND, "City not found");
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
    //     }

    //     CreateResponseDTO<City> response = new CreateResponseDTO<City>(city, HttpStatus.OK);
    //     return ResponseEntity.ok(response);
    // }

    // @GetMapping
    // public ResponseEntity<Object> getAllCities() {
    //     List<City> cities = cityService.findAllCities();
    //     CreateResponseDTO<List<City>> response = new CreateResponseDTO<List<City>>(cities, HttpStatus.OK);
    //     return ResponseEntity.ok(response);
    // }

    // @PutMapping()
    // public ResponseEntity<Object> updateCity(@Valid @RequestBody City city) {
    //     City existingCity = cityService.findCityById(city.getId());

    //     if (existingCity == null) {
    //         CreateResponseDTO<City> notFoundResponse = new CreateResponseDTO<City>
    //                 (HttpStatus.NOT_FOUND, "City not found");
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
    //     }

    //     City updatedCity = cityService.updateCity(city);
    //     CreateResponseDTO<City> response = new CreateResponseDTO<City>(updatedCity, HttpStatus.OK);
    //     return ResponseEntity.ok(response);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Object> deleteCity(@PathVariable Long id) {
    //     City existingCity = cityService.findCityById(id);

    //     if (existingCity == null) {
    //         CreateResponseDTO<City> notFoundResponse = new CreateResponseDTO<City>
    //                 (HttpStatus.NOT_FOUND, "City not found");
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
    //     }

    //     cityService.deleteCity(id);
    //     CreateResponseDTO<City> response = new CreateResponseDTO<City>(HttpStatus.OK, "City deleted");
    //     return ResponseEntity.ok(response);
    // }
}
