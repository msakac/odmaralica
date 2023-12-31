package org.foi.diplomski.msakac.odmaralica.service;

import org.foi.diplomski.msakac.odmaralica.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    void uploadImage(MultipartFile file, Long userId, Long accommodationUnitId, Long residenceId);

    byte[] getImage(String id);

    List<Long> findImageIds(String query);

    Image findById(Long id);

    void delete(Long id);

    void deleteForType(String type, Long id);
}
