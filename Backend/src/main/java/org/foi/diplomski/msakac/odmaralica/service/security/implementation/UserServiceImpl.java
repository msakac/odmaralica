package org.foi.diplomski.msakac.odmaralica.service.security.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.foi.diplomski.msakac.odmaralica.dto.security.AuthenticatedUserDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegisterRequestDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegisterResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;
import org.foi.diplomski.msakac.odmaralica.exceptions.EmailAlreadyExistException;
import org.foi.diplomski.msakac.odmaralica.exceptions.InvalidPasswordFormatException;
import org.foi.diplomski.msakac.odmaralica.mapper.security.UserMapper;
import org.foi.diplomski.msakac.odmaralica.model.Role;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.repository.RoleRepository;
import org.foi.diplomski.msakac.odmaralica.repository.UserRepository;
import org.foi.diplomski.msakac.odmaralica.service.security.IUserService;
import org.foi.diplomski.msakac.odmaralica.utils.GenericRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private static final String REGISTRATION_ROLE = "user";

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User registration(RegisterRequestDTO registrationRequest) {
        checkRegisterRequest(registrationRequest);

        final User user = User.builder()
            .name(registrationRequest.getName())
            .surname(registrationRequest.getSurname())
            .password(bCryptPasswordEncoder.encode(registrationRequest.getPassword()))
            .email(registrationRequest.getEmail())
            .role(getRole())
            .activated(false).build();
        userRepository.save(user);

        return user;
    }

    @Override
    public AuthenticatedUserDTO findAuthenticatedUserByEmail(String email) {
        User exampleUser = new User();
        exampleUser.setEmail(email);

        final User user = GenericRepository.findOneByExample(exampleUser, userRepository);

        return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    private void checkRegisterRequest(RegisterRequestDTO registrationRequest) {
        final String email = registrationRequest.getEmail();
        final boolean existsByEmail = userRepository.existsByEmail(email);
        if (existsByEmail) {
            throw new EmailAlreadyExistException(email);
        }
        final String password = registrationRequest.getPassword();
        if (password.length() < 8) {
            throw new InvalidPasswordFormatException();
        }
    }

    private Role getRole() {
        Role role = roleRepository.findByRole(REGISTRATION_ROLE);
        if (role == null) {
            role = new Role();
            role.setRole(REGISTRATION_ROLE);
            roleRepository.save(role);
        }
        return role;
    }

}
