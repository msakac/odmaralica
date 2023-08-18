package org.foi.diplomski.msakac.odmaralica.security.service;

import lombok.RequiredArgsConstructor;
import org.foi.diplomski.msakac.odmaralica.model.Role;
import org.foi.diplomski.msakac.odmaralica.security.dto.AuthenticatedUserDto;
import org.foi.diplomski.msakac.odmaralica.security.model.CustomUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String EMAIL_OR_PASSWORD_INVALID = "Invalid email or password.";

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {

        final AuthenticatedUserDto authenticatedUser = userService.findAuthenticatedUserByEmail(email);

        if (Objects.isNull(authenticatedUser)) {
            throw new UsernameNotFoundException(EMAIL_OR_PASSWORD_INVALID);
        }

        final String authenticatedEmail = authenticatedUser.getEmail();
        final String authenticatedPassword = authenticatedUser.getPassword();
        final Long authenticatedId = authenticatedUser.getId();
        final Role userRole = authenticatedUser.getRole();
        final SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.getRole());

        //TODO dodati za aktivaciju korisnickog racuna
        return new CustomUser(authenticatedEmail, authenticatedPassword, Collections.singletonList(grantedAuthority), authenticatedId);
    }
}
