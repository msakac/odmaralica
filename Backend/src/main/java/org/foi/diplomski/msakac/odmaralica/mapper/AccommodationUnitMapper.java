package org.foi.diplomski.msakac.odmaralica.mapper;

import org.foi.diplomski.msakac.odmaralica.dto.get.AccommodationUnitGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.AccommodationUnitPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AccommodationUnitPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.AccommodationUnit;
import org.foi.diplomski.msakac.odmaralica.model.Residence;
import org.foi.diplomski.msakac.odmaralica.service.implementation.ResidenceServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AccommodationUnitMapper {
    @Autowired
    protected ResidenceServiceImpl residenceService;
    protected ResidenceMapper residenceMapper = Mappers.getMapper(ResidenceMapper.class);

    @Mapping(source = "accommodationUnitPostDTO.residenceId", target = "residence")
    public abstract AccommodationUnit convert(AccommodationUnitPostDTO accommodationUnitPostDTO);

    @Mapping(source = "accommodationUnitPutDTO.residenceId", target = "residence")
    public abstract AccommodationUnit convert(AccommodationUnitPutDTO accommodationUnitPutDTO);

    public abstract AccommodationUnitGetDTO convert(AccommodationUnit residence);

    protected Residence mapToResidence(long residenceId){
        ResidenceGetDTO residenceGet = residenceService.findById(residenceId); 
        return residenceMapper.convert(residenceGet);
    }
}
