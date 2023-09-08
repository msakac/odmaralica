package org.foi.diplomski.msakac.odmaralica.dto.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CustomAccommodationUnitDTO {
    private Long id;
    private String name;
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
    private List<CustomPricePeriodDTO> pricePeriods;
    private List<String> availableDates;
    private List<Long> imagesIds;
}
