package org.foi.diplomski.msakac.odmaralica.dto.custom;

import java.util.List;

import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceTypeGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class ResidenceAggregateDTO {
    private Long id;
    private String name;
    private ResidenceTypeGetDTO type;
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
    private List<Long> imageIds;
    private CustomAddressDTO address;
    private List<CustomAccommodationUnitDTO> units;
}
