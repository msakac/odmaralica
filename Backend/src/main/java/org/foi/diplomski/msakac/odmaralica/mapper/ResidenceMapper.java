package org.foi.diplomski.msakac.odmaralica.mapper;

import org.foi.diplomski.msakac.odmaralica.dto.post.ResidencePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ResidencePutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Residence;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.security.service.UserServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ResidenceMapper {
    @Autowired
    protected UserServiceImpl userService;

    @Mapping(source = "residencePostDTO.ownerId", target = "owner")
    public abstract Residence convert(ResidencePostDTO residencePostDTO);

    @Mapping(source = "residencePutDTO.ownerId", target = "owner")
    public abstract Residence convert(ResidencePutDTO residencePutDTO);

    protected User mapToUser(Long ownerId) {
        return userService.findById(ownerId);
    }
}
