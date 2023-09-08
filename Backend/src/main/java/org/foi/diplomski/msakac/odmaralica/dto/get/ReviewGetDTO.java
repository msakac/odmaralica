package org.foi.diplomski.msakac.odmaralica.dto.get;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReviewGetDTO {
    private Long id;
    private ResidenceGetDTO residence;
    private UserGetDTO user;
    private double grade;
    private String message;
}
