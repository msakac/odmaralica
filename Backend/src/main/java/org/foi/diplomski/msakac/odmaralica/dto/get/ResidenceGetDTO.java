package org.foi.diplomski.msakac.odmaralica.dto.get;

import org.foi.diplomski.msakac.odmaralica.security.dto.UserGetDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResidenceGetDTO {
    private Long id;
    private String name;
    private String type;
    private String description;
    private UserGetDTO owner;
}
