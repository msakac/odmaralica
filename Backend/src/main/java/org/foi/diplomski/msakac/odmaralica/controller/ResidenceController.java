package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.controller.base.AbstractBaseController;
import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.custom.ResidenceAggregateDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ResidencePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ResidencePutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Residence;
import org.foi.diplomski.msakac.odmaralica.service.implementation.ResidenceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/residence")
public class ResidenceController extends AbstractBaseController<Residence, ResidenceGetDTO, ResidencePostDTO, ResidencePutDTO, ResidenceServiceImpl> {

    @Autowired
    public ResidenceController(ResidenceServiceImpl service) {
        super(service);
    }

    @Override
    public CreateResponseDTO<Residence> getNotFoundResponse() {
        return new CreateResponseDTO<Residence>(HttpStatus.NOT_FOUND, "Residence not found");
    }

    @GetMapping("/aggregate")
    public ResponseEntity<Object> getAllAgregateResidences() {
        List<ResidenceAggregateDTO> residences = service.aggregateAllData();
        CreateResponseDTO<List<ResidenceAggregateDTO>> response = new CreateResponseDTO<List<ResidenceAggregateDTO>>(residences, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/aggregate/{id}")
    public ResponseEntity<Object> getAggregateResidence(@PathVariable Long id) {
        ResidenceAggregateDTO aggregateResidence = service.aggregateData(id);
        if (aggregateResidence == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getNotFoundResponse());
        }
        CreateResponseDTO<ResidenceAggregateDTO> response = new CreateResponseDTO<ResidenceAggregateDTO>(aggregateResidence, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }
}
