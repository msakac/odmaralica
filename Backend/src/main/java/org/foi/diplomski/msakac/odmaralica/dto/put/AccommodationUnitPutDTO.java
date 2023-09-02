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
public class AccommodationUnitPutDTO {
    @NotNull(message = "Id cannot be empty")
    private Long id;

    @NotNull(message = "Residence ID cannot be empty")
    private Long residenceId;

    @NotEmpty(message = "Accommodation unit name cannot be empty")
    private String name;

    @NotEmpty(message = "Accommodation unit description cannot be empty")
    private String description;
    private String unitSize;
    private String numOfGuests;
    private String beds;
    private Boolean privateKitchen;
    private Boolean privateBathroom;
    private Boolean terrace;
    private Boolean seaView;
    private Boolean tv;
    private Boolean pets;
    private Boolean smoking;
}
