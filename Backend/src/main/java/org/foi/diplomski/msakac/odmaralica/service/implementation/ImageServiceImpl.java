package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.model.Image;
import org.foi.diplomski.msakac.odmaralica.repository.ImageRepository;
import org.foi.diplomski.msakac.odmaralica.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements IImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image storeImage(MultipartFile file) {
        try {
            Image image = new Image();
            image.setImage(file.getBytes());
            return imageRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image: " + e.getMessage());
        }
    }
}
