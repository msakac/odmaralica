package org.foi.diplomski.msakac.odmaralica.service.security;

import org.foi.diplomski.msakac.odmaralica.dto.security.PrivacyRequestGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.PrivacyRequestPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.PrivacyRequestPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.security.PrivacyRequest;

import java.util.List;

import javax.validation.Valid;

public interface IPrivacyRequestService {
    PrivacyRequest convertPost(PrivacyRequestPostDTO entityPost);

    PrivacyRequest convertPut(PrivacyRequestPutDTO entityPut);

    PrivacyRequestGetDTO convertGet(PrivacyRequest entity);

    PrivacyRequestGetDTO create(PrivacyRequestPostDTO entityPost);

    PrivacyRequestGetDTO findById(Long id);

    List<PrivacyRequestGetDTO> findAll();

    PrivacyRequestGetDTO update(PrivacyRequestPutDTO entityPut);

    void delete(Long id);

    List<PrivacyRequestGetDTO> find(String queryParams);
    
    PrivacyRequestGetDTO acceptRequest(@Valid Long id);

    Class<PrivacyRequest> getEntityClass();
}
