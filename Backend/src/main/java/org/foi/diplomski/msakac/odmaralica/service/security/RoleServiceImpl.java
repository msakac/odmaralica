package org.foi.diplomski.msakac.odmaralica.service.security;

import lombok.RequiredArgsConstructor;

import org.foi.diplomski.msakac.odmaralica.dto.security.RoleRequestDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.security.RoleMapper;
import org.foi.diplomski.msakac.odmaralica.model.Role;
import org.foi.diplomski.msakac.odmaralica.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public Role save(RoleRequestDTO roleRequest) {
        final Role role = RoleMapper.INSTANCE.convertToRole(roleRequest);
        return roleRepository.save(role);
    }

    public Role find(String name) {
        return roleRepository.findByRole(name);
    }
}

