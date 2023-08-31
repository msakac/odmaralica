package org.foi.diplomski.msakac.odmaralica.mapper;

import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceTypeGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ResidencePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ResidencePutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Residence;
import org.foi.diplomski.msakac.odmaralica.model.ResidenceType;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.service.implementation.ResidenceTypeServiceImpl;
import org.foi.diplomski.msakac.odmaralica.service.security.implementation.UserServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ResidenceMapper {
    @Autowired
    protected UserServiceImpl userService;
    @Autowired
    protected ResidenceTypeServiceImpl residenceTypeService;

    protected ResidenceTypeMapper residenceTypeMapper = Mappers.getMapper(ResidenceTypeMapper.class);

    @Mapping(source = "residencePostDTO.ownerId", target = "owner")
    @Mapping(source = "residencePostDTO.residenceTypeId", target = "residenceType")
    public abstract Residence convert(ResidencePostDTO residencePostDTO);

    @Mapping(source = "residencePutDTO.residenceTypeId", target = "residenceType")
    @Mapping(source = "residencePutDTO.ownerId", target = "owner")
    public abstract Residence convert(ResidencePutDTO residencePutDTO);

    public abstract ResidenceGetDTO convert(Residence residence);

    public abstract Residence convert(ResidenceGetDTO residenceGetDTO);

    protected User mapToOwner(Long ownerId) {
        return userService.findById(ownerId);
    }

    protected ResidenceType mapToResidenceType(Long residenceTypeId) {
        ResidenceTypeGetDTO residenceGet = residenceTypeService.findById(residenceTypeId);
        return residenceTypeMapper.convert(residenceGet);
    }
}
