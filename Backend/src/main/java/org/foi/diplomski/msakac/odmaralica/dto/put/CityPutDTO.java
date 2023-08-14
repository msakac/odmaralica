package org.foi.diplomski.msakac.odmaralica.dto.put;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CityPutDTO {
    @NotNull(message = "Id cannot be empty")
    private Long id;

    @NotEmpty(message = "City name cannot be empty")
    private String name;

    @NotEmpty(message = "ZIP code cannot be empty")
    private String zip;

    @NotNull(message = "Region ID cannot be empty")
    private Long regionId;
}
