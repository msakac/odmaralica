package org.foi.diplomski.msakac.odmaralica.service;

import java.util.List;
import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceTypeGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ResidenceTypePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ResidenceTypePutDTO;
import org.foi.diplomski.msakac.odmaralica.model.ResidenceType;

public interface IResidenceTypeService {
    ResidenceType convertPost(ResidenceTypePostDTO entityPost);

    ResidenceType convertPut(ResidenceTypePutDTO entityPut);

    ResidenceTypeGetDTO convertGet(ResidenceType entity);

    ResidenceTypeGetDTO create(ResidenceTypePostDTO entityPost);

    ResidenceTypeGetDTO findById(Long id);

    ResidenceType findByName(String name);

    List<ResidenceTypeGetDTO> findAll();

    ResidenceTypeGetDTO update(ResidenceTypePutDTO entityPut);

    void delete(Long id);

    List<ResidenceTypeGetDTO> find(String queryParams);

    Class<ResidenceType> getEntityClass();
}
