package org.foi.diplomski.msakac.odmaralica.service.implementation;

import javax.persistence.EntityManager;
import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ResidencePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ResidencePutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.ResidenceMapper;
import org.foi.diplomski.msakac.odmaralica.model.Residence;
import org.foi.diplomski.msakac.odmaralica.repository.ResidenceRepository;
import org.foi.diplomski.msakac.odmaralica.service.ResidenceService;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenceServiceImpl extends AbstractBaseService<Residence, ResidenceRepository, ResidenceMapper, ResidenceGetDTO, ResidencePostDTO, ResidencePutDTO> implements ResidenceService {
    //FIXME: vlasnik apartmana nebre biti korisnik koji nije iznajmljivac
    @Autowired
    public ResidenceServiceImpl(ResidenceRepository repository, ResidenceMapper mapper, EntityManager entityManager) {
        super(repository, mapper, entityManager);
    }

    @Override
    public Residence convertPost(ResidencePostDTO entityPost) {
        return mapper.convert(entityPost);
    }

    @Override
    public Residence convertPut(ResidencePutDTO entityPut) {
        return mapper.convert(entityPut);
    }

    @Override
    public ResidenceGetDTO convertGet(Residence entity) {
        return mapper.convert(entity);
    }

    public Class<Residence> getEntityClass() {
        return Residence.class;
    }
}
