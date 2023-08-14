package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.get.CityGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.CityPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.CityPutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.CityMapper;
import org.foi.diplomski.msakac.odmaralica.model.City;
import org.foi.diplomski.msakac.odmaralica.repository.CityRepository;
import org.foi.diplomski.msakac.odmaralica.service.CityService;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class CityServiceImpl extends AbstractBaseService<City, CityRepository, CityMapper, CityGetDTO, CityPostDTO, CityPutDTO> implements CityService {

    @Autowired
    public CityServiceImpl(CityRepository repository, CityMapper mapper, EntityManager entityManager) {
        super(repository, mapper, entityManager);
    }

    @Override
    public City convertPost(CityPostDTO entityPost) {
        return mapper.convert(entityPost);
    }

    @Override
    public City convertPut(CityPutDTO entityPut) {
        return mapper.convert(entityPut);
    }

    @Override
    public CityGetDTO convertGet(City entity) {
        return mapper.convert(entity);
    }

    public Class<City> getEntityClass() {
        return City.class;
    }
}
