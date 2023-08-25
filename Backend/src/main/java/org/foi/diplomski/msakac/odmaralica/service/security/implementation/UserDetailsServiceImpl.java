package org.foi.diplomski.msakac.odmaralica.service.security.implementation;

import lombok.RequiredArgsConstructor;

import org.foi.diplomski.msakac.odmaralica.model.Role;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.model.security.CustomUser;
import org.foi.diplomski.msakac.odmaralica.service.security.IUserService;
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

    private final IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {

        final User user = userService.findByEmail(email);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(EMAIL_OR_PASSWORD_INVALID);
        }

        final String authenticatedEmail = user.getEmail();
        final String authenticatedPassword = user.getPassword();
        final Long authenticatedId = user.getId();
        final Role userRole = user.getRole();
        final SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.getRole());

        //TODO dodati za aktivaciju korisnickog racuna
        return new CustomUser(authenticatedEmail, authenticatedPassword, Collections.singletonList(grantedAuthority), authenticatedId);
    }
}
