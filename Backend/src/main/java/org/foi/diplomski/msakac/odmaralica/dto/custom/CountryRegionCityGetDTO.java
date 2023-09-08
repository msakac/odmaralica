package org.foi.diplomski.msakac.odmaralica.dto.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CountryRegionCityGetDTO {
    private Long id;
    private String name;
    private List<CustomRegionDTO> regions;
}
