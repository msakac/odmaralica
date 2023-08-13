package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.controller.base.AbstractBaseController;
import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.CityPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.CityPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.City;
import org.foi.diplomski.msakac.odmaralica.service.implementation.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController extends AbstractBaseController<City, CityPostDTO, CityPutDTO, CityServiceImpl> {
    @Autowired
    public CityController(CityServiceImpl service) {
        super(service);
    }

    @Override
    public CreateResponseDTO<City> getNotFoundResponse() {
        return new CreateResponseDTO<City>(HttpStatus.NOT_FOUND, "City not found");
    }
}
