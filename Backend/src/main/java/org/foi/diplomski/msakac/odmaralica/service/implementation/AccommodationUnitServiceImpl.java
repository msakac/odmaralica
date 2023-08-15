package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.get.AccommodationUnitGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.AccommodationUnitPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AccommodationUnitPutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.AccommodationUnitMapper;
import org.foi.diplomski.msakac.odmaralica.model.AccommodationUnit;
import org.foi.diplomski.msakac.odmaralica.repository.AccommodationUnitRepository;
import org.foi.diplomski.msakac.odmaralica.service.AccommodationUnitService;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class AccommodationUnitServiceImpl extends AbstractBaseService<AccommodationUnit, AccommodationUnitRepository, AccommodationUnitMapper, AccommodationUnitGetDTO, AccommodationUnitPostDTO, AccommodationUnitPutDTO> implements AccommodationUnitService {

    @Autowired
    public AccommodationUnitServiceImpl(AccommodationUnitRepository repository, AccommodationUnitMapper mapper, EntityManager entityManager) {
        super(repository, mapper, entityManager);
    }

    @Override
    public AccommodationUnitGetDTO create(AccommodationUnitPostDTO entityPost) {
        //find if accommodation unit with same residenceId and name exists
        AccommodationUnit existingAccommodationUnit = repository.findByResidenceIdAndName(entityPost.getResidenceId(), entityPost.getName());
        if (existingAccommodationUnit != null) {
            throw new RuntimeException("Accommodation unit in this residence with this name already exists");
        }
        return super.create(entityPost);
    }

    @Override
    public AccommodationUnit convertPost(AccommodationUnitPostDTO entityPost) {
        return mapper.convert(entityPost);
    }

    @Override
    public AccommodationUnit convertPut(AccommodationUnitPutDTO entityPut) {
        return mapper.convert(entityPut);
    }

    @Override
    public AccommodationUnitGetDTO convertGet(AccommodationUnit entity) {
        return mapper.convert(entity);
    }

    public Class<AccommodationUnit> getEntityClass() {
        return AccommodationUnit.class;
    }
}
