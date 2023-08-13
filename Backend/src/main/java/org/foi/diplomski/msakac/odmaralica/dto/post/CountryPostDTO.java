package org.foi.diplomski.msakac.odmaralica.dto.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CountryPostDTO {
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Country code cannot be empty")
    private String countryCode;
}
