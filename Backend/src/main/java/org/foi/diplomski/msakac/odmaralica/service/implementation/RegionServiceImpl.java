package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.get.RegionGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.RegionPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.RegionPutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.RegionMapper;
import org.foi.diplomski.msakac.odmaralica.model.Region;
import org.foi.diplomski.msakac.odmaralica.repository.RegionRepository;
import org.foi.diplomski.msakac.odmaralica.service.IRegionService;
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
public class RegionServiceImpl implements IRegionService {

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
    public Region create(RegionPostDTO regionPost) {
        final Region region = regionMapper.convertToRegion(regionPost);
        return regionRepository.save(region);
    }

    @Override
    public Region findByName(String name) {
        return regionRepository.findByName(name);
    }

    @Override
    public RegionGetDTO findById(Long id) {
        Region region = regionRepository.findById(id).orElse(null);
        return regionMapper.convertToRegionGetDTO(region);
    }

    @Override
    public List<RegionGetDTO> findAll() {
        List<Region> regions = regionRepository.findAll();
        List<RegionGetDTO> getRegions = new ArrayList<>();
        for (Region region : regions) {
            getRegions.add(regionMapper.convertToRegionGetDTO(region));
        }
        return getRegions;
    }

    @Override
    public Region update(RegionPutDTO regionPut) {
        final Region region = regionMapper.convertToRegion(regionPut);
        return regionRepository.save(region);
    }

    @Override
    public void delete(Long id) {
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
