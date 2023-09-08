package org.foi.diplomski.msakac.odmaralica.dto.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserPostDTO {
    @NotEmpty(message = "Accommodation unit name cannot be empty")
    private String name;

    @NotEmpty(message = "Surname cannot be empty")
    private String surname;

    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "Role cannot be empty")
    private Long roleId;

    @NotNull(message = "Activated cannot be empty")
    private Boolean activated;
}


//sto zasto i kako

// korisnik prije bilo kakve rezervacije treba procitati politku privatnosti i
// prihvatiti ju da ima
// omoguciti povlacenje privole o sukladnosti o politiki privatnosti ali ako nema niti jednu aktivnu
// ili upcoming rezervaciju ili mu odgotiti rezervacije

// 