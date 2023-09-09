package org.foi.diplomski.msakac.odmaralica.controller.security;

import org.foi.diplomski.msakac.odmaralica.controller.base.AbstractBaseController;
import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.PrivacyRequestGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.PrivacyRequestPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.PrivacyRequestPutDTO;
import org.foi.diplomski.msakac.odmaralica.exceptions.InvalidRequestResponseBuilder;
import org.foi.diplomski.msakac.odmaralica.model.security.PrivacyRequest;
import org.foi.diplomski.msakac.odmaralica.service.security.implementation.PrivacyRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/privacy-request")
public class PrivacyRequestController extends AbstractBaseController<PrivacyRequest, PrivacyRequestGetDTO, PrivacyRequestPostDTO, PrivacyRequestPutDTO, PrivacyRequestServiceImpl> {

    @Autowired
    public PrivacyRequestController(PrivacyRequestServiceImpl service) {
        super(service);
    }

    @Override
    public CreateResponseDTO<PrivacyRequest> getNotFoundResponse() {
        return new CreateResponseDTO<PrivacyRequest>(HttpStatus.NOT_FOUND, "Privacy request not found");
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<Object> acceptPrivacyRequest(@PathVariable Long id) {
        try {
            PrivacyRequestGetDTO acceptedRequest = service.acceptRequest(id);
            return ResponseEntity.ok(new CreateResponseDTO<PrivacyRequestGetDTO>(acceptedRequest, HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(InvalidRequestResponseBuilder.createResponse(e));
        }
    }
}
