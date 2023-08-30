package org.foi.diplomski.msakac.odmaralica.service;

import org.foi.diplomski.msakac.odmaralica.dto.get.RegionGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.RegionPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.RegionPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Region;

import java.util.List;

public interface IRegionService {
    Region create(RegionPostDTO region);

    Region findByName(String name);

    RegionGetDTO findById(Long id);

    List<RegionGetDTO> findAll();

    Region update(RegionPutDTO region);

    void delete(Long id);

    List<Region> find(String queryParams);
}
