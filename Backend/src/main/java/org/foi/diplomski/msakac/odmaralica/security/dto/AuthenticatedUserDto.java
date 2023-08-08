package org.foi.diplomski.msakac.odmaralica.security.dto;
import org.foi.diplomski.msakac.odmaralica.model.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AuthenticatedUserDto {

	private String name;

	private String surname;

	private String email;

	private String password;

	private Role role;

}
