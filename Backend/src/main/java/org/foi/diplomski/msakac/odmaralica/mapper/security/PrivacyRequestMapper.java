package org.foi.diplomski.msakac.odmaralica.mapper.security;

import org.foi.diplomski.msakac.odmaralica.dto.security.PrivacyRequestGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.PrivacyRequestPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.PrivacyRequestPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.model.security.PrivacyRequest;
import org.foi.diplomski.msakac.odmaralica.service.security.implementation.UserServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PrivacyRequestMapper {
    @Autowired
    protected UserServiceImpl userService;

    @Mapping(source = "privacyRequestPostDTO.userId", target = "user")
    public abstract PrivacyRequest convert(PrivacyRequestPostDTO privacyRequestPostDTO);

    @Mapping(source = "privacyRequestPutDTO.userId", target = "user")
    @Mapping(source = "privacyRequestPutDTO.acceptedById", target = "acceptedBy")
    public abstract PrivacyRequest convert(PrivacyRequestPutDTO privacyRequestPutDTO);

    public abstract PrivacyRequestGetDTO convert(PrivacyRequest privacyRequest);

    protected User mapToUser(Long userId) {
        if (userId == null) {
            return null;
        }
        return userService.findById(userId);
    }


}
