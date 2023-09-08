package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.custom.CountryRegionCityGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.custom.CustomCityDTO;
import org.foi.diplomski.msakac.odmaralica.dto.custom.CustomRegionDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.CountryPostDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.CountryMapper;
import org.foi.diplomski.msakac.odmaralica.model.City;
import org.foi.diplomski.msakac.odmaralica.model.Country;
import org.foi.diplomski.msakac.odmaralica.model.Region;
import org.foi.diplomski.msakac.odmaralica.repository.CityRepository;
import org.foi.diplomski.msakac.odmaralica.repository.CountryRepository;
import org.foi.diplomski.msakac.odmaralica.repository.RegionRepository;
import org.foi.diplomski.msakac.odmaralica.service.ICountryService;
import org.foi.diplomski.msakac.odmaralica.utils.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements ICountryService {

    private final CountryRepository countryRepository;
    private final EntityManager entityManager;
    private final RegionRepository regionRepository;
    private final CityRepository cityRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, EntityManager entityManager, RegionRepository regionRepository, CityRepository cityRepository) {
        this.countryRepository = countryRepository;
        this.entityManager = entityManager;
        this.regionRepository = regionRepository;
        this.cityRepository = cityRepository;
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

    public List<CountryRegionCityGetDTO> findAllWithRegionsAndCities() {
        List<Country> countries = countryRepository.findAll();
        List<CountryRegionCityGetDTO> countryRegionCityGetDTOS = new ArrayList<>();
        for (Country country : countries) {
            List<CustomRegionDTO> customRegionDTOS = getRegionsWithCountry(country.getId());
            CountryRegionCityGetDTO countryRegionCityGetDTO = new CountryRegionCityGetDTO(country.getId(), country.getName(), customRegionDTOS);
            countryRegionCityGetDTOS.add(countryRegionCityGetDTO);
        }
        return countryRegionCityGetDTOS;
    }

    private List<CustomRegionDTO> getRegionsWithCountry(Long id) {
        List<Region> regions = regionRepository.findAllByCountryId(id);
        List<CustomRegionDTO> customRegionDTOList = new ArrayList<>();
        for (Region region : regions) {
            List<CustomCityDTO> customCityDTOList = getCitiesWithRegion(region.getId());
            CustomRegionDTO customRegionDTO = new CustomRegionDTO(region.getId(), region.getName(), customCityDTOList);
            customRegionDTOList.add(customRegionDTO);
        }
        return customRegionDTOList;
    }

    private List<CustomCityDTO> getCitiesWithRegion(Long id) {
        List<City> cities = cityRepository.findAllByRegionId(id);
        List<CustomCityDTO> customCityDTOList = new ArrayList<>();
        for (City city : cities) {
            CustomCityDTO customCityDTO = new CustomCityDTO(city.getId(), city.getName(), city.getZip());
            customCityDTOList.add(customCityDTO);
        }
        return customCityDTOList;
    }
}
