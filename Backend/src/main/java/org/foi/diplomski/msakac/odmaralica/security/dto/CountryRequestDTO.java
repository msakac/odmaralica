package org.foi.diplomski.msakac.odmaralica.security.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CountryRequestDTO {
    @NotEmpty(message = "Name cannot be empty")
	private String name;
    @NotEmpty(message = "Name cannot be empty")
	private String countryCode;
}
