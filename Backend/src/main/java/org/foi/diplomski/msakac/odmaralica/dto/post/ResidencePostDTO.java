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
public class ResidencePostDTO {
    @NotEmpty(message = "Residence name cannot be empty")
    private String name;

    @NotEmpty(message = "Residence type cannot be empty")
    private Long residenceTypeId;

    @NotEmpty(message = "Residence description cannot be empty")
    private String description;

    @NotNull(message = "Owner ID cannot be empty")
    private Long ownerId;
}
