package org.foi.diplomski.msakac.odmaralica.dto.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.foi.diplomski.msakac.odmaralica.model.Role;


@Getter
@Setter
@NoArgsConstructor
public class AuthenticatedUserDTO {
    //TODO dodati za aktivaciju korisnickog racuna

    private Long id;

    private String name;

    private String surname;

    private String email;

    private Role role;

    private boolean activated;

}
