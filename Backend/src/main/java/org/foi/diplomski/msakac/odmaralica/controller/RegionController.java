package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.RegionPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.RegionPutDto;
import org.foi.diplomski.msakac.odmaralica.model.Country;
import org.foi.diplomski.msakac.odmaralica.model.Region;
import org.foi.diplomski.msakac.odmaralica.service.CountryService;
import org.foi.diplomski.msakac.odmaralica.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {

    private final RegionService regionService;

    private final CountryService countryService;

    private final CreateResponseDTO<Region> notFoundResponse = new CreateResponseDTO<Region>
            (HttpStatus.NOT_FOUND, "Region not found");

    private final CreateResponseDTO<Region> countryNotFoundResponse = new CreateResponseDTO<Region>
            (HttpStatus.NOT_FOUND, "Country not found");

    private final CreateResponseDTO<Region> conflictResponse = new CreateResponseDTO<Region>
            (HttpStatus.CONFLICT, "Name already exists!");

    @Autowired
    public RegionController(RegionService regionService, CountryService countryService) {
        this.regionService = regionService;
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<Object> createRegion(@Valid @RequestBody RegionPostDTO region) {

        Region existingName = regionService.findByName(region.getName());
        Country existingCountry = countryService.findById(region.getCountryId());
        if (existingName != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(conflictResponse);
        }

        if(existingCountry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(countryNotFoundResponse);
        }
        Region createdRegion = regionService.create(region);
        CreateResponseDTO<Region> response = new CreateResponseDTO<Region>(createdRegion, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRegionById(@PathVariable Long id) {
        Region region = regionService.findById(id);

        if (region == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }

        CreateResponseDTO<Region> response = new CreateResponseDTO<Region>(region, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Object> getAllRegions() {
        List<Region> regions = regionService.findAll();
        CreateResponseDTO<List<Region>> response = new CreateResponseDTO<List<Region>>(regions, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public ResponseEntity<Object> updateRegion(@Valid @RequestBody RegionPutDto region) {
        Region existingName = regionService.findByName(region.getName());

        if (existingName != null && !existingName.getId().equals(region.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(conflictResponse);
        }

        Country existingCountry = countryService.findById(region.getCountryId());
        if(existingCountry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(countryNotFoundResponse);
        }

        final Region updatedRegion = regionService.update(region);
        CreateResponseDTO<Region> response = new CreateResponseDTO<Region>(updatedRegion, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRegion(@PathVariable Long id) {
        Region existingRegion = regionService.findById(id);

        if (existingRegion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }

        regionService.delete(id);
        CreateResponseDTO<Region> response = new CreateResponseDTO<Region>(HttpStatus.OK, "Region deleted");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find")
    public ResponseEntity<Object> queryCountries(@RequestParam("q") String queryParams) {
        // FIXME: Osim q parametara trebam jos sort=, offset, limit
        List<Region> regions = regionService.find(queryParams);

        if (regions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }

        CreateResponseDTO<List<Region>> response = new CreateResponseDTO<List<Region>>(regions, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }
}
