package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.model.Region;
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
    private final CreateResponseDTO<Region> notFoundResponse = new CreateResponseDTO<Region>
            (HttpStatus.NOT_FOUND, "Region not found");
    private final CreateResponseDTO<Region> conflictResponse = new CreateResponseDTO<Region>
            (HttpStatus.CONFLICT, "Name already exists!");

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping
    public ResponseEntity<Object> createRegion(@Valid @RequestBody Region region) {
        Region existingName = regionService.findByName(region.getName());

        if (existingName != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(conflictResponse);
        }

        Region createdRegion = regionService.createRegion(region);
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

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRegion(@PathVariable Long id,
                                               @Valid @RequestBody Region region) {
        Region existingRegion = regionService.findById(id);

        if (existingRegion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }

        Region existingName = regionService.findByName(region.getName());

        if (existingName != null && !existingName.getId().equals(existingRegion.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(conflictResponse);
        }

        existingRegion.setName(region.getName());

        final Region updatedRegion = regionService.updateRegion(existingRegion);
        CreateResponseDTO<Region> response = new CreateResponseDTO<Region>(updatedRegion, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRegion(@PathVariable Long id) {
        Region existingRegion = regionService.findById(id);

        if (existingRegion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }

        regionService.deleteRegion(id);
        CreateResponseDTO<Region> response = new CreateResponseDTO<Region>(HttpStatus.OK, "Region deleted");
        return ResponseEntity.ok(response);
    }
}
