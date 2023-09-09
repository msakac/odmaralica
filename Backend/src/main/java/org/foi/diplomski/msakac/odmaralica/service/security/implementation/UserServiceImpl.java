package org.foi.diplomski.msakac.odmaralica.service.security.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.security.AuthenticatedUserDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegisterRequestDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserPostDTO;
import org.foi.diplomski.msakac.odmaralica.exceptions.EmailAlreadyExistException;
import org.foi.diplomski.msakac.odmaralica.exceptions.InvalidPasswordFormatException;
import org.foi.diplomski.msakac.odmaralica.mapper.security.UserMapper;
import org.foi.diplomski.msakac.odmaralica.model.Role;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.repository.RoleRepository;
import org.foi.diplomski.msakac.odmaralica.repository.UserRepository;
import org.foi.diplomski.msakac.odmaralica.service.security.IUserService;
import org.foi.diplomski.msakac.odmaralica.utils.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private static final String REGISTRATION_ROLE = "user";

    private final UserRepository userRepository;

    private final UserMapper mapper;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper mapper, RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User registration(RegisterRequestDTO registrationRequest) {
        checkRegisterRequest(registrationRequest);

        final User user = User.builder()
                .name(registrationRequest.getName())
                .surname(registrationRequest.getSurname())
                .password(bCryptPasswordEncoder.encode(registrationRequest.getPassword()))
                .email(registrationRequest.getEmail())
                .role(getRole())
                .policyAccepted(false)
                .activated(false).build();
        userRepository.save(user);

        return user;
    }

    @Override
    public User createUser(UserPostDTO user) {
        User userDto = mapper.convertToUser(user);
        userDto.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(userDto);
    }

    @Override
    public AuthenticatedUserDTO findAuthenticatedUserByEmail(String email) {
        final User user = userRepository.findByEmail(email);
        return mapper.convertToAuthenticatedUserDto(user);
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

    @Override
    public User update(User user) {
        //check if password is the same as in database, if not encode it

        Long userId = SecurityConstants.getAuthenticatedUserId();
        User checkUser = findById(userId);

        if (checkUser.getRole().getRole().equals("user") || checkUser.getRole().getRole().equals("renter")) {
            if (!checkUser.getId().equals(userId)) {
                return null;
            }
        }

        User samePasswordUser = userRepository.findById(user.getId()).orElse(null);
        if (!samePasswordUser.getPassword().equals(user.getPassword()) && user.getPassword().length() != 0) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(samePasswordUser.getPassword());
        }
        return userRepository.save(user);
    }

    public User updateSecure(User user) {
        return userRepository.save(user);
    }

    @Override
    public AuthenticatedUserDTO getAuthenticatedUser(Long id) {
        if (id == SecurityConstants.getAuthenticatedUserId()) {
            User user = findById(id);
            AuthenticatedUserDTO authenticatedUserDTO = mapper.convertToAuthenticatedUserDto(user);
            return authenticatedUserDTO;
        }
        return null;
    }

    @Override
    public UserGetDTO findByIdDTO(Long id) {
        User user = findById(id);
        UserGetDTO userGetDTO = mapper.convertToUserGetDTO(user);
        return userGetDTO;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
