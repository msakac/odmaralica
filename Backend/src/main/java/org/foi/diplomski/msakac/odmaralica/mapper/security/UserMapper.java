package org.foi.diplomski.msakac.odmaralica.mapper.security;

import org.foi.diplomski.msakac.odmaralica.dto.security.AuthenticatedUserDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserPostDTO;
import org.foi.diplomski.msakac.odmaralica.model.Role;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.service.security.IRoleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

    @Autowired
    protected IRoleService roleService;

    public abstract AuthenticatedUserDTO convertToAuthenticatedUserDto(User user);

    public abstract User convertToUser(AuthenticatedUserDTO authenticatedUserDto);

    public abstract UserGetDTO convertToUserGetDTO(User user);

    @Mapping(source = "userPostDTO.roleId", target = "role")
    public abstract User convertToUser(UserPostDTO userPostDTO);

    protected Role mapToRole(Long roleId) {
        return roleService.findById(roleId);
    }

}
