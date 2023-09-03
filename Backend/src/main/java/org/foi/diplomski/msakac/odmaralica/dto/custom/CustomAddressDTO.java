package org.foi.diplomski.msakac.odmaralica.dto.custom;

import org.foi.diplomski.msakac.odmaralica.dto.get.CityGetDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
