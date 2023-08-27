package org.foi.diplomski.msakac.odmaralica.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;
import org.apache.commons.io.FilenameUtils;
import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ImagePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.LoginRequestDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.LoginResponseDTO;
import org.foi.diplomski.msakac.odmaralica.exceptions.InvalidRequestResponseBuilder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private static String imageDirectory = System.getProperty("user.dir") + "/images/";

    @PostMapping(produces = {MediaType.IMAGE_PNG_VALUE, "application/json"}, consumes = {"multipart/form-data"})
    public ResponseEntity<Object> upload(@Valid @RequestParam("imageFile")MultipartFile file, @RequestParam("imageName") String name) {
        try {
            makeDirectoryIfNotExist(imageDirectory);
            Path fileNamePath = Paths.get(imageDirectory, name.concat(".").concat(FilenameUtils.getExtension(file.getOriginalFilename())));
            Files.write(fileNamePath, file.getBytes());
            CreateResponseDTO<LoginResponseDTO> imageResponse = new CreateResponseDTO<LoginResponseDTO>(HttpStatus.OK, "Created");
            return ResponseEntity.ok(imageResponse);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(InvalidRequestResponseBuilder.createResponse(e));
        }
    }

    @GetMapping(value = "/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws IOException {
        Path imagePath = Paths.get(imageDirectory, imageName + ".png"); // Assuming PNG format, adjust if needed
        byte[] imageBytes = Files.readAllBytes(imagePath);
        ByteArrayResource resource = new ByteArrayResource(imageBytes);
        return ResponseEntity.ok()
                .contentLength(imageBytes.length)
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }

    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}
