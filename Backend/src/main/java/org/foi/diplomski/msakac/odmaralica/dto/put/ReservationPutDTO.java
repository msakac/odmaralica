package org.foi.diplomski.msakac.odmaralica.dto.put;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReservationPutDTO {
    @NotNull(message = "Id cannot be empty")
    private Long id;

    @NotNull(message = "User ID cannot be empty")
    private String userEmail;

    @NotNull(message = "Accommodation unit ID cannot be empty")
    private Long accommodationUnitId;

    @NotNull(message = "Start date cannot be empty")
    private Date startAt;

    @NotNull(message = "End date cannot be empty")
    private Date endAt;

    @NotNull(message = "Cancelled cannot be empty")
    private boolean cancelled;

    private Timestamp createdAt;

    private String note;
}
