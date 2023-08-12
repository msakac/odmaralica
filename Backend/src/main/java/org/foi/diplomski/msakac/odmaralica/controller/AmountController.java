package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.AmountPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AmountPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Amount;
import org.foi.diplomski.msakac.odmaralica.service.implementation.AmountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/amount")
public class AmountController extends BaseController<Amount, AmountPostDTO, AmountPutDTO, AmountServiceImpl> {
    @Autowired
    public AmountController(AmountServiceImpl service) {
        super(service);
    }

    @Override
    protected CreateResponseDTO<Amount> getNotFoundResponse() {
        return new CreateResponseDTO<Amount>(HttpStatus.NOT_FOUND, "Amount not found");
    }

}
