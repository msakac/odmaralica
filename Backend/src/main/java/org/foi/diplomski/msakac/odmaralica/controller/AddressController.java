package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.controller.base.AbstractBaseController;
import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.AddressGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.AddressPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AddressPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Address;
import org.foi.diplomski.msakac.odmaralica.service.implementation.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController extends AbstractBaseController<Address, AddressGetDTO, AddressPostDTO, AddressPutDTO, AddressServiceImpl>{

    @Autowired
    public AddressController(AddressServiceImpl service) {
        super(service);
    }

    @Override
    public CreateResponseDTO<Address> getNotFoundResponse() {
        return new CreateResponseDTO<Address>(HttpStatus.NOT_FOUND, "Address not found");
    }
}
