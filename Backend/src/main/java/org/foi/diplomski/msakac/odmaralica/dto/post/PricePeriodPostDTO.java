package org.foi.diplomski.msakac.odmaralica.dto.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PricePeriodPostDTO {
    @NotNull(message = "Accommodation unit ID cannot be empty")
    private Long accommodationUnitId;

    @NotNull(message = "Start date cannot be empty")
    private Date startAt;

    @NotNull(message = "End date cannot be empty")
    private Date endAt;

    @NotNull(message = "Amount ID cannot be empty")
    private Long amountId;
}
