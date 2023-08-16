package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.controller.base.AbstractBaseController;
import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.PricePeriodGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.PricePeriodPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.PricePeriodPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.PricePeriod;
import org.foi.diplomski.msakac.odmaralica.service.implementation.PricePeriodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price-period")
public class PricePeriodController extends AbstractBaseController<PricePeriod, PricePeriodGetDTO, PricePeriodPostDTO, PricePeriodPutDTO, PricePeriodServiceImpl> {
    @Autowired
    public PricePeriodController(PricePeriodServiceImpl service) {
        super(service);
    }

    @Override
    public CreateResponseDTO<PricePeriod> getNotFoundResponse() {
        return new CreateResponseDTO<PricePeriod>(HttpStatus.NOT_FOUND, "PricePeriod not found");
    }

}
