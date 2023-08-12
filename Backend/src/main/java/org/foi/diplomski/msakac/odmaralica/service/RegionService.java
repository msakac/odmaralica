package org.foi.diplomski.msakac.odmaralica.service;

import org.foi.diplomski.msakac.odmaralica.dto.post.RegionPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.RegionPutDto;
import org.foi.diplomski.msakac.odmaralica.model.Region;

import java.util.List;

public interface RegionService {
    Region create(RegionPostDTO region);
    Region findByName(String name);
    Region findById(Long id);
    List<Region> findAll();
    Region update(RegionPutDto region);
    void delete(Long id);
    List<Region> find(String queryParams);
}
