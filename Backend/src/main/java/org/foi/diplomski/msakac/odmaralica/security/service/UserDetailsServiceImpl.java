package org.foi.diplomski.msakac.odmaralica.security.service;

import org.foi.diplomski.msakac.odmaralica.model.UserRole;
import org.foi.diplomski.msakac.odmaralica.security.dto.AuthenticatedUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
		final UserRole userRole = authenticatedUser.getUserRole();
		final SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.name());

		return new User(authenticatedEmail, authenticatedPassword, Collections.singletonList(grantedAuthority));
	}
}
