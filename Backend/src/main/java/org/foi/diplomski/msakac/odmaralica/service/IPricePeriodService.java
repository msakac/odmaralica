package org.foi.diplomski.msakac.odmaralica.service;

import java.util.List;
import org.foi.diplomski.msakac.odmaralica.dto.get.PricePeriodGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.PricePeriodPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.PricePeriodPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.PricePeriod;

public interface IPricePeriodService {
    PricePeriod convertPost(PricePeriodPostDTO entityPost);

    PricePeriod convertPut(PricePeriodPutDTO entityPut);

    PricePeriodGetDTO convertGet(PricePeriod entity);

    PricePeriodGetDTO create(PricePeriodPostDTO entityPost);

    PricePeriodGetDTO findById(Long id);

    List<PricePeriodGetDTO> findAll();

    PricePeriodGetDTO update(PricePeriodPutDTO entityPut);

    void delete(Long id);

    List<PricePeriodGetDTO> find(String queryParams);

    Class<PricePeriod> getEntityClass();
}
