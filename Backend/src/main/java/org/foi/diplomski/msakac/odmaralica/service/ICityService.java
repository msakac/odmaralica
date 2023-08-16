package org.foi.diplomski.msakac.odmaralica.service;

import org.foi.diplomski.msakac.odmaralica.dto.get.CityGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.CityPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.CityPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.City;

import java.util.List;

public interface ICityService {
    City convertPost(CityPostDTO entityPost);

    City convertPut(CityPutDTO entityPut);

    CityGetDTO convertGet(City entityPut);

    CityGetDTO create(CityPostDTO entityPost);

    CityGetDTO findById(Long id);

    List<CityGetDTO> findAll();

    CityGetDTO update(CityPutDTO entityPut);

    void delete(Long id);

    List<CityGetDTO> find(String queryParams);

    Class<City> getEntityClass();
}
