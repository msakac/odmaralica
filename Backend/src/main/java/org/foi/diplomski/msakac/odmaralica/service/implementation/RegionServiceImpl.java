package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.post.RegionPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.RegionPutDto;
import org.foi.diplomski.msakac.odmaralica.mapper.RegionMapper;
import org.foi.diplomski.msakac.odmaralica.model.Region;
import org.foi.diplomski.msakac.odmaralica.repository.RegionRepository;
import org.foi.diplomski.msakac.odmaralica.service.RegionService;
import org.foi.diplomski.msakac.odmaralica.utils.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;
    private final EntityManager entityManager;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository, RegionMapper regionMapper, EntityManager entityManager) {
        this.regionRepository = regionRepository;
        this.regionMapper = regionMapper;
        this.entityManager = entityManager;
    }

    @Override
    public Region createRegion(RegionPostDTO regionPost) {
        final Region region = regionMapper.convertToRegion(regionPost);
        return regionRepository.save(region);
    }

    @Override
    public Region findByName(String name) {
        return regionRepository.findByName(name);
    }

    @Override
    public Region findById(Long id) {
        return regionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    @Override
    public Region updateRegion(RegionPutDto regionPut) {
        final Region region = regionMapper.convertToRegion(regionPut);
        return regionRepository.save(region);
    }

    @Override
    public void deleteRegion(Long id) {
        regionRepository.deleteById(id);
    }

    @Override
    public List<Region> find(String queryParams) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        QueryBuilder<Region> queryBuilder = new QueryBuilder<>(criteriaBuilder, Region.class);

        CriteriaQuery<Region> query = queryBuilder.buildQuery(queryParams);

        TypedQuery<Region> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultList();
    }
}
