package org.foi.diplomski.msakac.odmaralica.mapper;

import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.ReviewGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ReviewPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ReviewPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Residence;
import org.foi.diplomski.msakac.odmaralica.model.Review;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.service.IResidenceService;
import org.foi.diplomski.msakac.odmaralica.service.security.implementation.UserServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ReviewMapper {
    @Autowired
    protected IResidenceService residenceService;
    @Autowired
    protected UserServiceImpl userService;
    protected ResidenceMapper residenceMapper = Mappers.getMapper(ResidenceMapper.class);

    @Mapping(source = "reviewPostDTO.residenceId", target = "residence")
    @Mapping(source = "reviewPostDTO.userId", target = "user")
    public abstract Review convert(ReviewPostDTO reviewPostDTO);

    @Mapping(source = "reviewPutDTO.residenceId", target = "residence")
    @Mapping(source = "reviewPutDTO.userId", target = "user")
    public abstract Review convert(ReviewPutDTO reviewPutDTO);

    public abstract ReviewGetDTO convert(Review review);

    protected User mapToUser(Long ownerId) {
        return userService.findById(ownerId);
    }

    protected Residence mapToResidence(Long residenceId) {
        ResidenceGetDTO residenceGet = residenceService.findById(residenceId);
        return residenceMapper.convert(residenceGet);
    }
}
