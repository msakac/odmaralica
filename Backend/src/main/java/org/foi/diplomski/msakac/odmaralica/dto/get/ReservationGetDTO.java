package org.foi.diplomski.msakac.odmaralica.dto.get;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReservationGetDTO {
    private Long id;
    private UserGetDTO user;
    private AccommodationUnitGetDTO accommodationUnit;
    private Date startAt;
    private Date endAt;
    private String note;
    private boolean cancelled;
    private LocalDateTime createdAt;
}
