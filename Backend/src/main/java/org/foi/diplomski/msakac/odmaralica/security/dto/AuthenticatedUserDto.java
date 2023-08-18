package org.foi.diplomski.msakac.odmaralica.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.foi.diplomski.msakac.odmaralica.model.Role;


@Getter
@Setter
@NoArgsConstructor
public class AuthenticatedUserDto {
    //TODO dodati za aktivaciju korisnickog racuna


    private Long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private Role role;

}
