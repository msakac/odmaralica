package org.foi.diplomski.msakac.odmaralica.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.exceptions.InvalidRequestResponseBuilder;
import org.foi.diplomski.msakac.odmaralica.service.IImageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final IImageService imageService;

    // When creating image I need file and resource to which it belongs
    @PostMapping(produces = {MediaType.IMAGE_PNG_VALUE, "application/json"}, consumes = {"multipart/form-data"})
    public ResponseEntity<Object> upload(@Valid @RequestParam("imageFile")MultipartFile file, 
        @RequestParam(value="userId", required = false) Long userId, 
        @RequestParam(value="accommodationUnitId", required = false) Long accommodationUnitId,
        @RequestParam(value="residenceId", required = false) Long residenceId) {

        try {
            imageService.uploadImage(file, userId, accommodationUnitId, residenceId);
            return ResponseEntity.ok().build();
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getImage(@PathVariable String id) throws IOException {
        try {
            byte[] imageBytes = imageService.getImage(id);
            ByteArrayResource resource = new ByteArrayResource(imageBytes);
            if (imageBytes == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok()
                    .contentLength(imageBytes.length)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/find")
    public ResponseEntity<Object> queryCountries(@RequestParam("q") String queryParams) {
        // FIXME: Osim q parametara trebam jos sort=, offset, limit
        try {
            List<Long> entities = imageService.findImageIds(queryParams);
            if (entities.isEmpty()) {
                CreateResponseDTO<Object> response = new CreateResponseDTO<Object>(HttpStatus.NOT_FOUND, "Entity not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.ok(new CreateResponseDTO<List<Long>>(entities, HttpStatus.OK));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(InvalidRequestResponseBuilder.createResponse(e));
        }


    }

}
