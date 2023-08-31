package org.foi.diplomski.msakac.odmaralica.service.implementation;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.foi.diplomski.msakac.odmaralica.model.AccommodationUnit;
import org.foi.diplomski.msakac.odmaralica.model.Image;
import org.foi.diplomski.msakac.odmaralica.model.Residence;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.repository.AccommodationUnitRepository;
import org.foi.diplomski.msakac.odmaralica.repository.ImageRepository;
import org.foi.diplomski.msakac.odmaralica.repository.ResidenceRepository;
import org.foi.diplomski.msakac.odmaralica.repository.UserRepository;
import org.foi.diplomski.msakac.odmaralica.service.IImageService;
import org.foi.diplomski.msakac.odmaralica.utils.QueryBuilder;
import org.foi.diplomski.msakac.odmaralica.utils.SecurityConstants;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements IImageService {

    private final UserRepository userRepository;
    private final AccommodationUnitRepository accommodationUnitRepository;
    private final ResidenceRepository residenceRepository;
    private final ImageRepository imageRepository;
    private final EntityManager entityManager;
    private static String imageDirectory = System.getProperty("user.dir") + "/images/";

    public void uploadImage(MultipartFile file, Long userId, Long accommodationUnitId, Long residenceId) {
        // Getting user, creator, accommodation unit and residence
        AccommodationUnit accommodationUnit = null;
        User user = null;
        Residence residence = null;
        if(userId != null){
            user = userRepository.findById(userId).orElse(null);
        }
        if(accommodationUnitId != null){
           accommodationUnit = accommodationUnitRepository.findById(accommodationUnitId).orElse(null);
        }
        if(residenceId != null) {
            residence = residenceRepository.findById(residenceId).orElse(null);
        }
        Long authenticatedUserId = SecurityConstants.getAuthenticatedUserId();
        User createdBy = userRepository.findById(authenticatedUserId).orElse(null);

        // Creating image object
        final Image image = Image.builder()
            .user(user)
            .accommodationUnit(accommodationUnit)
            .residence(residence)
            .createdBy(createdBy)
            .createdAt(new Timestamp(System.currentTimeMillis()))
            .build();
        Image savedImage = imageRepository.save(image);


        // Getting original file name and extension
        String originalFileName = file.getOriginalFilename();
        String imageId = savedImage.getId().toString();

        // Image name will look like id_originalFileName.fileExtension
        String imageName = String.format("%s_%s", imageId, originalFileName);

        // Saving image to disk
        try {
            String imageDirectory = getImageDirectoy();
            Path fileNamePath = Paths.get(imageDirectory, imageName);
            Files.write(fileNamePath, file.getBytes());
        } catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public byte[] getImage(String id) {
        String imageNameToFind = id + "_";
        try {
            List<Path> matchingImagePaths = Files.list(Paths.get(imageDirectory))
                    .filter(path -> path.getFileName().toString().startsWith(imageNameToFind))
                    .collect(Collectors.toList());

            if (matchingImagePaths.isEmpty()) {
                return null;
            }
            byte[] imageBytes = Files.readAllBytes(matchingImagePaths.get(0));
            return imageBytes;
        } catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Long> findImageIds(String queryParams) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        QueryBuilder<Image> queryBuilder = new QueryBuilder<>(criteriaBuilder, Image.class);
        CriteriaQuery<Image> query = queryBuilder.buildQuery(queryParams);
        TypedQuery<Image> typedQuery = entityManager.createQuery(query);

        List<Long> imageIds = new ArrayList<>();
        for (Image entity : typedQuery.getResultList()) {
            imageIds.add(entity.getId());
        }
        return imageIds;
    }

    private String getImageDirectoy() {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
        return imageDirectory;
    }
}
