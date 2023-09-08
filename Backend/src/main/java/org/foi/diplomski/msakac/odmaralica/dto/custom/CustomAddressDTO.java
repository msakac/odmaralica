package org.foi.diplomski.msakac.odmaralica.dto.custom;

import lombok.*;
import org.foi.diplomski.msakac.odmaralica.dto.get.CityGetDTO;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class CustomAddressDTO {
    private Long id;
    private String street;
    private CityGetDTO city;
    private String additional;
}
