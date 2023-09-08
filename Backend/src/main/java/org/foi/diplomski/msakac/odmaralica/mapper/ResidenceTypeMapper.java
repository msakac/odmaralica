package org.foi.diplomski.msakac.odmaralica.mapper;

import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceTypeGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ResidenceTypePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ResidenceTypePutDTO;
import org.foi.diplomski.msakac.odmaralica.model.ResidenceType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResidenceTypeMapper {
    ResidenceTypeMapper INSTANCE = Mappers.getMapper(ResidenceTypeMapper.class);

    ResidenceType convert(ResidenceTypePutDTO ResidenceTypePutDTO);

    ResidenceType convert(ResidenceTypePostDTO ResidenceTypePostDTO);

    ResidenceTypeGetDTO convert(ResidenceType ResidenceType);

    ResidenceType convert(ResidenceTypeGetDTO ResidenceTypeGetDTO);
}
