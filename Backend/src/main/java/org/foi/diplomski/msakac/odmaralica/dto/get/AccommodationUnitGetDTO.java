package org.foi.diplomski.msakac.odmaralica.dto.get;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccommodationUnitGetDTO {
    private Long id;
    private String name;
    private String description;
    private ResidenceGetDTO residence;
}
