package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.get.ReviewGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ReviewPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ReviewPutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.ReviewMapper;
import org.foi.diplomski.msakac.odmaralica.model.Reservation;
import org.foi.diplomski.msakac.odmaralica.model.Review;
import org.foi.diplomski.msakac.odmaralica.repository.ReservationRepository;
import org.foi.diplomski.msakac.odmaralica.repository.ReviewRepository;
import org.foi.diplomski.msakac.odmaralica.service.IReviewService;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl extends AbstractBaseService<Review, ReviewRepository, ReviewMapper, ReviewGetDTO, ReviewPostDTO, ReviewPutDTO> implements IReviewService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository repository, ReviewMapper mapper, EntityManager entityManager, ReservationRepository reservationRepository) {
        super(repository, mapper, entityManager);
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReviewGetDTO create(ReviewPostDTO entityPost) {
        // Get all reservations for user
        List<Reservation> userReservations = reservationRepository.findByUserId(entityPost.getUserId());

        // Find reservation for this residence
        List<Reservation> userReservationsForResidence = new ArrayList<>();

        for (Reservation reservation : userReservations) {
            if (reservation.getAccommodationUnit().getResidence().getId().equals(entityPost.getResidenceId())) {
                userReservationsForResidence.add(reservation);
            }
        }
        // Find reservations that are in past
        List<Reservation> userReservationsForResidenceInPast = new ArrayList<>();

        for (Reservation reservation : userReservationsForResidence) {
            if (reservation.getEndAt().before(new Date(System.currentTimeMillis()))) {
                userReservationsForResidenceInPast.add(reservation);
            }
        }

        List<Review> reviewsForResidence = repository.findByResidenceIdAndUserId(entityPost.getResidenceId(), entityPost.getUserId());

        if (userReservationsForResidenceInPast.size() > reviewsForResidence.size()) {
            return super.create(entityPost);
        } else {
            throw new RuntimeException("User cannot review this residence");
        }
    }

    public Boolean canUserReview(Long userId, Long residenceId) {
        // Get all reservations for user
        List<Reservation> userReservations = reservationRepository.findByUserId(userId);

        // Find reservation for this residence
        List<Reservation> userReservationsForResidence = new ArrayList<>();

        for (Reservation reservation : userReservations) {
            if (reservation.getAccommodationUnit().getResidence().getId().equals(residenceId)) {
                userReservationsForResidence.add(reservation);
            }
        }
        // Find reservations that are in past
        List<Reservation> userReservationsForResidenceInPast = new ArrayList<>();

        for (Reservation reservation : userReservationsForResidence) {
            if (reservation.getEndAt().before(new Date(System.currentTimeMillis()))) {
                userReservationsForResidenceInPast.add(reservation);
            }
        }

        List<Review> reviewsForResidence = repository.findByResidenceIdAndUserId(residenceId, userId);

        if (userReservationsForResidenceInPast.size() > reviewsForResidence.size()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review convertPost(ReviewPostDTO entityPost) {
        return mapper.convert(entityPost);
    }

    @Override
    public Review convertPut(ReviewPutDTO entityPut) {
        return mapper.convert(entityPut);
    }

    @Override
    public ReviewGetDTO convertGet(Review entity) {
        return mapper.convert(entity);
    }

    public Class<Review> getEntityClass() {
        return Review.class;
    }
}
