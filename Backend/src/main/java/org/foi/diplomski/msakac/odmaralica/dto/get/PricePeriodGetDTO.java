package org.foi.diplomski.msakac.odmaralica.dto.get;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor

public class PricePeriodGetDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private AccommodationUnitGetDTO accommodationUnit;
    private AmountGetDTO amount;
}
