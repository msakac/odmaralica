package org.foi.diplomski.msakac.odmaralica.service.security;

import org.foi.diplomski.msakac.odmaralica.dto.security.AuthenticatedUserDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.RegisterRequestDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserPostDTO;
import org.foi.diplomski.msakac.odmaralica.model.User;

import java.util.List;


public interface IUserService {

    User registration(RegisterRequestDTO registrationRequest);

    AuthenticatedUserDTO findAuthenticatedUserByEmail(String email);

    AuthenticatedUserDTO getAuthenticatedUser(Long id);

    User update(User user);
    
    User updateSecure(User user);

    User findById(Long id);

    User findByEmail(String email);

    UserGetDTO findByIdDTO(Long id);

    List<User> findAll();

    User createUser(UserPostDTO user);

    void delete(Long id);
}
