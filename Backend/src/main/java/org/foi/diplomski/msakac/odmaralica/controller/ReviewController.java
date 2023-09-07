package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.controller.base.AbstractBaseController;
import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.ReviewGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ReviewPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ReviewPutDTO;
import org.foi.diplomski.msakac.odmaralica.exceptions.InvalidRequestResponseBuilder;
import org.foi.diplomski.msakac.odmaralica.model.Review;
import org.foi.diplomski.msakac.odmaralica.service.implementation.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController extends AbstractBaseController<Review, ReviewGetDTO, ReviewPostDTO, ReviewPutDTO, ReviewServiceImpl> {
    @Autowired
    public ReviewController(ReviewServiceImpl service) {
        super(service);
    }

    @GetMapping("/can-review/{userId}/{residenceId}")
    public ResponseEntity<Object> delete(@PathVariable Long userId, @PathVariable Long residenceId) {
        try {
            boolean canReview = service.canUserReview(userId, residenceId);
            return ResponseEntity.ok(new CreateResponseDTO<Boolean>(canReview, HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(InvalidRequestResponseBuilder.createResponse(e));
        }
    }

    @Override
    public CreateResponseDTO<Review> getNotFoundResponse() {
        return new CreateResponseDTO<Review>(HttpStatus.NOT_FOUND, "Review not found");
    }

}
