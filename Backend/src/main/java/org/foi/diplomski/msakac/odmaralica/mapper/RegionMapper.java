package org.foi.diplomski.msakac.odmaralica.mapper;

import org.foi.diplomski.msakac.odmaralica.dto.get.RegionGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.RegionPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.RegionPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Country;
import org.foi.diplomski.msakac.odmaralica.model.Region;
import org.foi.diplomski.msakac.odmaralica.service.ICountryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RegionMapper {
    @Autowired
    protected ICountryService countryService;

    @Mapping(source = "regionPostDTO.countryId", target = "country")
    public abstract Region convertToRegion(RegionPostDTO regionPostDTO);

    @Mapping(source = "regionPutDTO.countryId", target = "country")
    public abstract Region convertToRegion(RegionPutDTO regionPutDTO);

    public abstract Region convertToRegion(RegionGetDTO regionGetDTO);

    public abstract RegionGetDTO convertToRegionGetDTO(Region region);

    protected Country mapToCountry(Long countryId) {
        return countryService.findById(countryId);
    }
}


