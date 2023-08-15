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
public class AccommodationUnitPostDTO {
    @NotNull(message = "Residence ID cannot be empty")
    private Long residenceId;

    @NotEmpty(message = "Accommodation unit name cannot be empty")
    private String name;

    @NotEmpty(message = "Accommodation unit description cannot be empty")
    private String description;
}
