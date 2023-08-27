package org.foi.diplomski.msakac.odmaralica.service;

import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    void uploadImage(MultipartFile file, Long userId, Long accommodationUnitId, Long residenceId);
    public byte[] getImage(String id);
}
