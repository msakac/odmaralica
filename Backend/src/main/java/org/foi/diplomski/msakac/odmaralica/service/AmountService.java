package org.foi.diplomski.msakac.odmaralica.service;

import org.foi.diplomski.msakac.odmaralica.dto.post.AmountPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AmountPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Amount;

import java.util.List;

public interface AmountService {
    Amount convertPost(AmountPostDTO entityPost);

    Amount convertPut(AmountPutDTO entityPut);

    Amount create(AmountPostDTO entityPost);

    Amount findById(Long id);

    List<Amount> findAll();

    Amount update(AmountPutDTO entityPut);

    void delete(Long id);

    List<Amount> find(String queryParams);

    Class<Amount> getEntityClass();
}
