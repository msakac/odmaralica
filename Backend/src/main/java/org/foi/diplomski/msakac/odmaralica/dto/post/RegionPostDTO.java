package org.foi.diplomski.msakac.odmaralica.dto.post;

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
public class RegionPostDTO {
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotNull(message = "Country cannot be empty")
    private Long countryId;
}
