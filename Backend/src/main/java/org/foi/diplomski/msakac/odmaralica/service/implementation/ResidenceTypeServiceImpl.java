package org.foi.diplomski.msakac.odmaralica.service.implementation;

import javax.persistence.EntityManager;

import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceTypeGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ResidenceTypePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ResidenceTypePutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.ResidenceTypeMapper;
import org.foi.diplomski.msakac.odmaralica.model.ResidenceType;
import org.foi.diplomski.msakac.odmaralica.repository.ResidenceTypeRepository;
import org.foi.diplomski.msakac.odmaralica.service.IResidenceTypeService;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenceTypeServiceImpl extends AbstractBaseService<ResidenceType, ResidenceTypeRepository, ResidenceTypeMapper, ResidenceTypeGetDTO, ResidenceTypePostDTO, ResidenceTypePutDTO> implements IResidenceTypeService {
    @Autowired
    public ResidenceTypeServiceImpl(ResidenceTypeRepository repository, ResidenceTypeMapper mapper, EntityManager entityManager) {
        super(repository, mapper, entityManager);
    }

    @Override
    public ResidenceTypeGetDTO create(ResidenceTypePostDTO entityPost) {
        //check if entity with same name exists
        if (repository.findByName(entityPost.getName()) != null) {
            //throw exception
            throw new RuntimeException("Residence type with same name already exists");
        }
        return super.create(entityPost);
    }

    @Override
    public ResidenceType convertPost(ResidenceTypePostDTO entityPost) {
        return mapper.convert(entityPost);
    }

    @Override
    public ResidenceType convertPut(ResidenceTypePutDTO entityPut) {
        return mapper.convert(entityPut);
    }

    @Override
    public ResidenceTypeGetDTO convertGet(ResidenceType entity) {
        return mapper.convert(entity);
    }

    public Class<ResidenceType> getEntityClass() {
        return ResidenceType.class;
    }

    @Override
    public ResidenceType findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public ResidenceTypeGetDTO update(ResidenceTypePutDTO entityPut) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
