package org.foi.diplomski.msakac.odmaralica.controller;

import java.util.List;

import org.foi.diplomski.msakac.odmaralica.controller.base.AbstractBaseController;
import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.custom.CountryRegionCityGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.custom.ResidenceAggregateDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ResidencePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ResidencePutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Residence;
import org.foi.diplomski.msakac.odmaralica.service.implementation.ResidenceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residence")
public class ResidenceController extends AbstractBaseController<Residence, ResidenceGetDTO, ResidencePostDTO, ResidencePutDTO, ResidenceServiceImpl>{

    @Autowired
    public ResidenceController(ResidenceServiceImpl service) {
        super(service);
    }

    @Override
    public CreateResponseDTO<Residence> getNotFoundResponse() {
        return new CreateResponseDTO<Residence>(HttpStatus.NOT_FOUND, "Residence not found");
    }
    @GetMapping("/aggregate")
    public ResponseEntity<Object> getAllCountriesWithRegionsAndCities() {
        List<ResidenceAggregateDTO> countries = service.aggregateData();
        CreateResponseDTO<List<ResidenceAggregateDTO>> response = new CreateResponseDTO<List<ResidenceAggregateDTO>>(countries, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }
}
