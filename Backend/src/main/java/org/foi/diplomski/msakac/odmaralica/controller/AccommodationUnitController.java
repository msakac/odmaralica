package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.controller.base.AbstractBaseController;
import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.AccommodationUnitGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.AccommodationUnitPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AccommodationUnitPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.AccommodationUnit;
import org.foi.diplomski.msakac.odmaralica.service.implementation.AccommodationUnitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accommodation-unit")
public class AccommodationUnitController extends AbstractBaseController<AccommodationUnit, AccommodationUnitGetDTO, AccommodationUnitPostDTO, AccommodationUnitPutDTO, AccommodationUnitServiceImpl> {

    @Autowired
    public AccommodationUnitController(AccommodationUnitServiceImpl service) {
        super(service);
    }

    @Override
    public CreateResponseDTO<AccommodationUnit> getNotFoundResponse() {
        return new CreateResponseDTO<AccommodationUnit>(HttpStatus.NOT_FOUND, "Accommodation unit not found");
    }
}