package org.foi.diplomski.msakac.odmaralica.dto.post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PricePeriodPostDTO {
    @NotNull(message = "Accommodation unit ID cannot be empty")
    private Long accommodationUnitId;

    @NotEmpty(message = "Start date cannot be empty")
    @DateTimeFormat(pattern = "dd.MM.yyyy.")
    private LocalDate startAt;

    @NotEmpty(message = "End date cannot be empty")
    @DateTimeFormat(pattern = "dd.MM.yyyy.")
    private LocalDate endAt;

    @NotNull(message = "Amount ID cannot be empty")
    private Long amountId;
}
