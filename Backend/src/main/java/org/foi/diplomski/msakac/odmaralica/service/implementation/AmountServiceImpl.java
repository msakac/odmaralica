package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.post.AmountPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AmountPutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.AmountMapper;
import org.foi.diplomski.msakac.odmaralica.model.Amount;
import org.foi.diplomski.msakac.odmaralica.repository.AmountRepository;
import org.foi.diplomski.msakac.odmaralica.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmountServiceImpl extends BaseService<Amount, AmountRepository, AmountMapper, AmountPostDTO, AmountPutDTO> {


    @Autowired
    public AmountServiceImpl(AmountRepository repository, AmountMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Amount convertPost(AmountPostDTO entityPost) {
        return mapper.convert(entityPost);
    }
    
    @Override
    public Amount convertPut(AmountPutDTO entityPut) {
        return mapper.convert(entityPut);
    }

}
