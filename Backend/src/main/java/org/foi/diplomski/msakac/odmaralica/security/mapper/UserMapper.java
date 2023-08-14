package org.foi.diplomski.msakac.odmaralica.security.mapper;

import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.security.dto.AuthenticatedUserDto;
import org.foi.diplomski.msakac.odmaralica.security.dto.RegistrationRequest;
import org.foi.diplomski.msakac.odmaralica.security.dto.UserGetDTO;
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
