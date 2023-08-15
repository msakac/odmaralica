package org.foi.diplomski.msakac.odmaralica.service;

import java.util.List;

import org.foi.diplomski.msakac.odmaralica.dto.get.AccommodationUnitGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.AccommodationUnitPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AccommodationUnitPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.AccommodationUnit;

public interface AccommodationUnitService {
    AccommodationUnit convertPost(AccommodationUnitPostDTO entityPost);

    AccommodationUnit convertPut(AccommodationUnitPutDTO entityPut);

    AccommodationUnitGetDTO convertGet(AccommodationUnit entity);

    AccommodationUnitGetDTO create(AccommodationUnitPostDTO entityPost);

    AccommodationUnitGetDTO findById(Long id);

    List<AccommodationUnitGetDTO> findAll();

    AccommodationUnitGetDTO update(AccommodationUnitPutDTO entityPut);

    void delete(Long id);

    List<AccommodationUnitGetDTO> find(String queryParams);

    Class<AccommodationUnit> getEntityClass();
}
