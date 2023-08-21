package org.foi.diplomski.msakac.odmaralica.service.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.foi.diplomski.msakac.odmaralica.dto.security.AuthenticatedUserDto;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegistrationRequest;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegistrationResponse;
import org.foi.diplomski.msakac.odmaralica.mapper.security.UserMapper;
import org.foi.diplomski.msakac.odmaralica.model.Role;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.repository.RoleRepository;
import org.foi.diplomski.msakac.odmaralica.repository.UserRepository;
import org.foi.diplomski.msakac.odmaralica.service.implementation.UserValidationService;
import org.foi.diplomski.msakac.odmaralica.utils.GenericRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

    private static final String REGISTRATION_ROLE = "user";

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserValidationService userValidationService;

    @Override
    public RegistrationResponse registration(RegistrationRequest registrationRequest) {

        userValidationService.validateUser(registrationRequest);

        final User user = UserMapper.INSTANCE.convertToUser(registrationRequest);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActivated(true);
        Role role = roleRepository.findByRole(REGISTRATION_ROLE);

        if (role == null) {
            role = new Role();
            role.setRole(REGISTRATION_ROLE);
            roleRepository.save(role);
        }
        user.setRole(role);
        userRepository.save(user);

        final String username = registrationRequest.getEmail();
        log.info("{} registered successfully!", username);

        return new RegistrationResponse("{} registered successfully!");
    }

    @Override
    public AuthenticatedUserDto findAuthenticatedUserByEmail(String email) {
        User exampleUser = new User();
        exampleUser.setEmail(email);

        final User user = GenericRepository.findOneByExample(exampleUser, userRepository);

        return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
