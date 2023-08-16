package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.controller.base.AbstractBaseController;
import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.ReservationGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ReservationPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ReservationPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Reservation;
import org.foi.diplomski.msakac.odmaralica.service.implementation.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController extends AbstractBaseController<Reservation, ReservationGetDTO, ReservationPostDTO, ReservationPutDTO, ReservationServiceImpl> {
    @Autowired
    public ReservationController(ReservationServiceImpl service) {
        super(service);
    }

    @Override
    public CreateResponseDTO<Reservation> getNotFoundResponse() {
        return new CreateResponseDTO<Reservation>(HttpStatus.NOT_FOUND, "Reservation not found");
    }

}
