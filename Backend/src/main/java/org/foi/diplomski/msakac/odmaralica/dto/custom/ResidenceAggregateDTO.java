package org.foi.diplomski.msakac.odmaralica.dto.custom;

import lombok.*;
import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceTypeGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;

import java.util.List;

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
    private List<CustomReviewGetDTO> reviews;
}
