package org.foi.diplomski.msakac.odmaralica.service;

import org.foi.diplomski.msakac.odmaralica.dto.post.CityPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.CityPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.City;

import java.util.List;

public interface CityService {
    City convertPost(CityPostDTO entityPost);

    City convertPut(CityPutDTO entityPut);

    City create(CityPostDTO entityPost);

    City findById(Long id);

    List<City> findAll();

    City update(CityPutDTO entityPut);

    void delete(Long id);

    List<City> find(String queryParams);

    Class<City> getEntityClass();
}
