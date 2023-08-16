package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.post.CountryPostDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.CountryMapper;
import org.foi.diplomski.msakac.odmaralica.model.Country;
import org.foi.diplomski.msakac.odmaralica.repository.CountryRepository;
import org.foi.diplomski.msakac.odmaralica.service.ICountryService;
import org.foi.diplomski.msakac.odmaralica.utils.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Service
public class CountryServiceImpl implements ICountryService {

    private final CountryRepository countryRepository;
    private final EntityManager entityManager;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, EntityManager entityManager) {
        this.countryRepository = countryRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Country create(CountryPostDTO countryPost) {
        final Country country = CountryMapper.INSTANCE.convertToCountry(countryPost);
        return countryRepository.save(country);
    }

    @Override
    public Country findByName(String name) {
        return countryRepository.findByName(name);
    }

    @Override
    public Country findByCountryCode(String countryCode) {
        return countryRepository.findByCountryCode(countryCode);
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country update(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public List<Country> find(String queryParams) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        QueryBuilder<Country> queryBuilder = new QueryBuilder<>(criteriaBuilder, Country.class);

        CriteriaQuery<Country> query = queryBuilder.buildQuery(queryParams);

        TypedQuery<Country> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultList();
    }

}
