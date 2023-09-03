package org.foi.diplomski.msakac.odmaralica.dto.custom;

import java.util.Date;
import org.foi.diplomski.msakac.odmaralica.dto.get.AmountGetDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
