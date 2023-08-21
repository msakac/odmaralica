package org.foi.diplomski.msakac.odmaralica.mapper.security;

import org.foi.diplomski.msakac.odmaralica.dto.security.AuthenticatedUserDto;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegistrationRequest;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User convertToUser(RegistrationRequest registrationRequest);

    AuthenticatedUserDto convertToAuthenticatedUserDto(User user);

    User convertToUser(AuthenticatedUserDto authenticatedUserDto);

    UserGetDTO convertToUserGetDTO(User user);

}
