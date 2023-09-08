package org.foi.diplomski.msakac.odmaralica.dto.get;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;
import org.foi.diplomski.msakac.odmaralica.model.ResidenceType;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResidenceGetDTO {
    private Long id;
    private String name;
    private ResidenceType type;
    private String description;
    private UserGetDTO owner;
    private Boolean isPublished;
    private Boolean isParkingFree;
    private Boolean isWifiFree;
    private Boolean isAirConFree;
    private String distanceSea;
    private String distanceStore;
    private String distanceBeach;
    private String distanceCenter;
}
