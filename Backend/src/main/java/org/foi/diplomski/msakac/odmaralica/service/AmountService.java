package org.foi.diplomski.msakac.odmaralica.service;

import org.foi.diplomski.msakac.odmaralica.dto.get.AmountGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.AmountPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AmountPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Amount;

import java.util.List;

public interface AmountService {
    Amount convertPost(AmountPostDTO entityPost);

    Amount convertPut(AmountPutDTO entityPut);

    AmountGetDTO convertGet(Amount entity);

    AmountGetDTO create(AmountPostDTO entityPost);

    AmountGetDTO findById(Long id);

    List<AmountGetDTO> findAll();

    AmountGetDTO update(AmountPutDTO entityPut);

    void delete(Long id);

    List<AmountGetDTO> find(String queryParams);

    Class<Amount> getEntityClass();
}
