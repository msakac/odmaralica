package org.foi.diplomski.msakac.odmaralica.security.dto;

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
    private String email;
    private String description;
}
