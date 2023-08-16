package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.get.PricePeriodGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.PricePeriodPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.PricePeriodPutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.PricePeriodMapper;
import org.foi.diplomski.msakac.odmaralica.model.PricePeriod;
import org.foi.diplomski.msakac.odmaralica.repository.PricePeriodRepository;
import org.foi.diplomski.msakac.odmaralica.service.IPricePeriodService;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class PricePeriodServiceImpl extends AbstractBaseService<PricePeriod, PricePeriodRepository, PricePeriodMapper, PricePeriodGetDTO, PricePeriodPostDTO, PricePeriodPutDTO> implements IPricePeriodService {


    @Autowired
    public PricePeriodServiceImpl(PricePeriodRepository repository, PricePeriodMapper mapper, EntityManager entityManager) {
        super(repository, mapper, entityManager);
    }

    @Override
    public PricePeriod convertPost(PricePeriodPostDTO entityPost) {
        return mapper.convert(entityPost);
    }

    @Override
    public PricePeriod convertPut(PricePeriodPutDTO entityPut) {
        return mapper.convert(entityPut);
    }

    @Override
    public PricePeriodGetDTO convertGet(PricePeriod entity) {
        return mapper.convert(entity);
    }

    public Class<PricePeriod> getEntityClass() {
        return PricePeriod.class;
    }
}
