package org.foi.diplomski.msakac.odmaralica.mapper.security;

import org.foi.diplomski.msakac.odmaralica.dto.security.AuthenticatedUserDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    AuthenticatedUserDTO convertToAuthenticatedUserDto(User user);

    User convertToUser(AuthenticatedUserDTO authenticatedUserDto);

    UserGetDTO convertToUserGetDTO(User user);

}
