package org.foi.diplomski.msakac.odmaralica.service;

import java.util.List;

import org.foi.diplomski.msakac.odmaralica.dto.custom.ResidenceAggregateDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ResidencePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ResidencePutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Residence;

public interface IResidenceService {
    Residence convertPost(ResidencePostDTO entityPost);

    Residence convertPut(ResidencePutDTO entityPut);

    ResidenceGetDTO convertGet(Residence entity);

    ResidenceGetDTO create(ResidencePostDTO entityPost);

    ResidenceGetDTO findById(Long id);

    List<ResidenceGetDTO> findAll();

    ResidenceGetDTO update(ResidencePutDTO entityPut);

    void delete(Long id);

    List<ResidenceGetDTO> find(String queryParams);

    List<ResidenceAggregateDTO> aggregateAllData();
    
    ResidenceAggregateDTO aggregateData(Long id);

    Class<Residence> getEntityClass();
}
