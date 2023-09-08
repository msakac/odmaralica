package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.controller.base.AbstractBaseController;
import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceTypeGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ResidenceTypePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ResidenceTypePutDTO;
import org.foi.diplomski.msakac.odmaralica.model.ResidenceType;
import org.foi.diplomski.msakac.odmaralica.service.implementation.ResidenceTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/residence-type")
public class ResidenceTypeController extends AbstractBaseController<ResidenceType, ResidenceTypeGetDTO, ResidenceTypePostDTO, ResidenceTypePutDTO, ResidenceTypeServiceImpl> {

    @Autowired
    public ResidenceTypeController(ResidenceTypeServiceImpl service) {
        super(service);
    }

    @Override
    public CreateResponseDTO<ResidenceType> getNotFoundResponse() {
        return new CreateResponseDTO<ResidenceType>(HttpStatus.NOT_FOUND, "Residence type not found");
    }
}
