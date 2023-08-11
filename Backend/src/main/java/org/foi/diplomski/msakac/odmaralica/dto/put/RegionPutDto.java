package org.foi.diplomski.msakac.odmaralica.dto.put;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegionPutDto {
    @NotNull(message = "Id cannot be empty")
    private Long id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotNull(message = "Country cannot be empty")
	private Long countryId;
}
