package org.foi.diplomski.msakac.odmaralica.dto.custom;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CountryRegionCityGetDTO {
    private Long id;
    private String name;
    private List<CustomRegionDTO> regions;
}
