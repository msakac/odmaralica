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

import java.util.List;

import javax.persistence.EntityManager;

@Service
public class PricePeriodServiceImpl extends AbstractBaseService<PricePeriod, PricePeriodRepository, PricePeriodMapper, PricePeriodGetDTO, PricePeriodPostDTO, PricePeriodPutDTO> implements IPricePeriodService {

    @Autowired
    public PricePeriodServiceImpl(PricePeriodRepository repository, PricePeriodMapper mapper, EntityManager entityManager) {
        super(repository, mapper, entityManager);
    }

    @Override
    public PricePeriodGetDTO create(PricePeriodPostDTO entityPost){
        PricePeriod period = convertPost(entityPost);
        this.validate(period);
        return super.create(entityPost);
    }

    @Override
    public PricePeriodGetDTO update(PricePeriodPutDTO entityPut) {
        PricePeriod period = convertPut(entityPut);
        this.validate(period);
        return super.update(entityPut);
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

    private void validate(PricePeriod entity){
        // Check if start date is after end date
        if(entity.getStartAt().compareTo(entity.getEndAt()) > 0) {
            throw new RuntimeException("Start date cannot be after end date");
        }

        List<PricePeriod> pricePeriods = repository.findByAccommodationUnitId(entity.getAccommodationUnit().getId());
        for (PricePeriod pricePeriod : pricePeriods) {
            if(pricePeriod.getId().equals(entity.getId())){
                continue;
            }
            // Check if dates overlap
            if (pricePeriod.getStartAt().compareTo(entity.getStartAt()) <= 0 && pricePeriod.getEndAt().compareTo(entity.getStartAt()) >= 0) {
                throw new RuntimeException("Price period overlaps with existing price period");
            }
        }
    }
}
