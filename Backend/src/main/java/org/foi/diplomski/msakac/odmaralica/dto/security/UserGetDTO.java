package org.foi.diplomski.msakac.odmaralica.dto.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserGetDTO {
    private String name;
    private String surname;
    //TODO mozda meknuti email
    private String email;
    private String description;
}
