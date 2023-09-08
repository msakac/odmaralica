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
public class AddressPostDTO {
    @NotNull(message = "Residence ID cannot be empty")
    private Long residenceId;
    @NotEmpty(message = "Street cannot be empty")
    private String street;
    @NotNull(message = "City ID cannot be empty")
    private Long cityId;
    private String additional;
}
