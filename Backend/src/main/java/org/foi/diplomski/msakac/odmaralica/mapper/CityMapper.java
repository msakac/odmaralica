package org.foi.diplomski.msakac.odmaralica.mapper;

import org.foi.diplomski.msakac.odmaralica.dto.get.CityGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.CityPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.CityPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.City;
import org.foi.diplomski.msakac.odmaralica.model.Region;
import org.foi.diplomski.msakac.odmaralica.service.RegionService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CityMapper {
    @Autowired
    protected RegionService regionService;

    @Mapping(source = "cityPostDTO.regionId", target = "region")
    public abstract City convert(CityPostDTO cityPostDTO);

    @Mapping(source = "cityPutDTO.regionId", target = "region")
    public abstract City convert(CityPutDTO cityPutDTO);

    public abstract CityGetDTO convert(City city);

    protected Region mapToRegion(Long regionId) {
        return regionService.findById(regionId);
    }
}
