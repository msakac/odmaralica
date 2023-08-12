package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.AmountPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AmountPutDto;
import org.foi.diplomski.msakac.odmaralica.model.Amount;
import org.foi.diplomski.msakac.odmaralica.service.implementation.AmountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/amounts")
public class AmountController extends BaseController<Amount, AmountPostDTO, AmountPutDto, AmountServiceImpl> {
    @Autowired
    public AmountController(AmountServiceImpl service) {
        super(service);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected CreateResponseDTO<Amount> getNotFoundResponse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNotFoundResponse'");
    }

}
