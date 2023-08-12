package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.post.CityPostDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.CityMapper;
import org.foi.diplomski.msakac.odmaralica.model.City;
import org.foi.diplomski.msakac.odmaralica.repository.CityRepository;
import org.foi.diplomski.msakac.odmaralica.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    // private final CityRepository cityRepository;
    // private final CityMapper cityMapper;

    // @Autowired
    // public CityServiceImpl(CityRepository cityRepository, CityMapper cityMapper) {
    //     this.cityRepository = cityRepository;
    //     this.cityMapper = cityMapper;
    // }

    // @Override
    // public City create(CityPostDTO cityPostDTO) {
    //     // final City city = cityMapper.convertToCity(cityPostDTO);
    //     return cityRepository.save(null);
    // }

    // @Override
    // public City findByNameAndZip(String name, String zip) {
    //     return cityRepository.findByNameAndZip(name, zip);
    // }

    // @Override
    // public City findById(Long id) {
    //     return cityRepository.findById(id).orElse(null);
    // }

    // @Override
    // public List<City> findAll() {
    //     return cityRepository.findAll();
    // }

    // @Override
    // public City update(City city) {
    //     return cityRepository.save(city);
    // }

    // @Override
    // public void delete(Long id) {
    //     cityRepository.deleteById(id);
    // }
}
