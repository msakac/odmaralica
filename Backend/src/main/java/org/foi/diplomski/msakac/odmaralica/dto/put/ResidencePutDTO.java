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
public class ResidencePutDTO {
    @NotNull(message = "Id cannot be empty")
    private Long id;

    @NotEmpty(message = "Residence name cannot be empty")
    private String name;

    @NotEmpty(message = "Residence type cannot be empty")
    private Long typeId;

    @NotEmpty(message = "Residence description cannot be empty")
    private String description;

    @NotNull(message = "Owner ID cannot be empty")
    private Long ownerId;

    private Boolean isPublished;
}
