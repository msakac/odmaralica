package org.foi.diplomski.msakac.odmaralica.dto.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.foi.diplomski.msakac.odmaralica.dto.get.AmountGetDTO;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CustomPricePeriodDTO {
    private Long id;
    private Date startAt;
    private Date endAt;
    private AmountGetDTO amount;
}
