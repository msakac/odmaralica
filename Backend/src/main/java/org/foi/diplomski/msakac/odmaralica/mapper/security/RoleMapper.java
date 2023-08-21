package org.foi.diplomski.msakac.odmaralica.mapper.security;

import org.foi.diplomski.msakac.odmaralica.dto.security.RoleRequestDTO;
import org.foi.diplomski.msakac.odmaralica.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role convertToRole(RoleRequestDTO roleRequest);
}
