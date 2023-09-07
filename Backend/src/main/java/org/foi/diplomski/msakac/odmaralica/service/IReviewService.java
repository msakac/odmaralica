package org.foi.diplomski.msakac.odmaralica.service;

import org.foi.diplomski.msakac.odmaralica.dto.get.ReviewGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ReviewPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ReviewPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Review;

import java.util.List;

public interface IReviewService {
    Review convertPost(ReviewPostDTO entityPost);

    Review convertPut(ReviewPutDTO entityPut);

    ReviewGetDTO convertGet(Review entity);

    ReviewGetDTO create(ReviewPostDTO entityPost);

    ReviewGetDTO findById(Long id);

    List<ReviewGetDTO> findAll();

    ReviewGetDTO update(ReviewPutDTO entityPut);

    void delete(Long id);

    List<ReviewGetDTO> find(String queryParams);

    Boolean canUserReview(Long userId, Long residenceId);

    Class<Review> getEntityClass();
}
