package org.foi.diplomski.msakac.odmaralica.dto.get;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddressGetDTO {
    private Long id;
    private ResidenceGetDTO residence;
    private String street;
    private CityGetDTO city;
    private String additional;
}
