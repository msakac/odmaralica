package org.foi.diplomski.msakac.odmaralica.dto.get;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.foi.diplomski.msakac.odmaralica.model.Country;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegionGetDTO {
    private Long id;
    private String name;
    private Country country;
}
