package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.get.AmountGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.AmountPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AmountPutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.AmountMapper;
import org.foi.diplomski.msakac.odmaralica.model.Amount;
import org.foi.diplomski.msakac.odmaralica.repository.AmountRepository;
import org.foi.diplomski.msakac.odmaralica.service.IAmountService;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class AmountServiceImpl extends AbstractBaseService<Amount, AmountRepository, AmountMapper, AmountGetDTO, AmountPostDTO, AmountPutDTO> implements IAmountService {


    @Autowired
    public AmountServiceImpl(AmountRepository repository, AmountMapper mapper, EntityManager entityManager) {
        super(repository, mapper, entityManager);
    }

    @Override
    public Amount convertPost(AmountPostDTO entityPost) {
        return mapper.convert(entityPost);
    }

    @Override
    public Amount convertPut(AmountPutDTO entityPut) {
        return mapper.convert(entityPut);
    }

    @Override
    public AmountGetDTO convertGet(Amount entity) {
        return mapper.convert(entity);
    }

    public Class<Amount> getEntityClass() {
        return Amount.class;
    }
}
