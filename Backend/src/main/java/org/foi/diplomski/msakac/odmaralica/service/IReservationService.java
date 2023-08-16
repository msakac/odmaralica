package org.foi.diplomski.msakac.odmaralica.service;

import java.util.List;

import org.foi.diplomski.msakac.odmaralica.dto.get.ReservationGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ReservationPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ReservationPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Reservation;

public interface IReservationService {
    Reservation convertPost(ReservationPostDTO entityPost);

    Reservation convertPut(ReservationPutDTO entityPut);

    ReservationGetDTO convertGet(Reservation entity);

    ReservationGetDTO create(ReservationPostDTO entityPost);

    ReservationGetDTO findById(Long id);

    List<ReservationGetDTO> findAll();

    ReservationGetDTO update(ReservationPutDTO entityPut);

    void delete(Long id);

    List<ReservationGetDTO> find(String queryParams);

    Class<Reservation> getEntityClass();
}
