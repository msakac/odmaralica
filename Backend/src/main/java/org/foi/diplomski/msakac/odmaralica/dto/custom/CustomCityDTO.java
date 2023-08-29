package org.foi.diplomski.msakac.odmaralica.dto.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CustomCityDTO {
    private Long id;
    private String name;
    private String zip;
}
