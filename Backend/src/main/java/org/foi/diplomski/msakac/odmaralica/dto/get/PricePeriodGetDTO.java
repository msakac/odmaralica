package org.foi.diplomski.msakac.odmaralica.dto.get;

import java.sql.Date;

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
    private Date startAt;
    private Date endAt;
    private AccommodationUnitGetDTO accommodationUnit;
    private AmountGetDTO amount;
}
