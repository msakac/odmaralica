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
public class RegionPutDTO {
    @NotNull(message = "Id cannot be empty")
    private Long id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotNull(message = "Country cannot be empty")
    private Long countryId;
}
