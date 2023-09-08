package org.foi.diplomski.msakac.odmaralica.repository;

import org.foi.diplomski.msakac.odmaralica.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByAccommodationUnitIdAndCancelledFalse(Long accommodationUnitId);

    List<Reservation> findByUserId(Long userId);
}
