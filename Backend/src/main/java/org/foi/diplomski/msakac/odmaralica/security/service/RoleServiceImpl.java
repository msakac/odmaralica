package org.foi.diplomski.msakac.odmaralica.security.service;

import org.foi.diplomski.msakac.odmaralica.repository.RoleRepository;
import org.foi.diplomski.msakac.odmaralica.model.Role;
import org.foi.diplomski.msakac.odmaralica.security.dto.RoleRequestDTO;
import org.foi.diplomski.msakac.odmaralica.security.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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

