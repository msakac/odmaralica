package org.foi.diplomski.msakac.odmaralica.service;

import org.foi.diplomski.msakac.odmaralica.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    Image storeImage(MultipartFile file);
}
