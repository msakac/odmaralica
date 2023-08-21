package org.foi.diplomski.msakac.odmaralica.dto.get;

import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class LogGetDTO {
    //TODO vrnuti sve atribute
    private Long id;
    private UserGetDTO user;
    private ActivityTypeGetDTO activityType;
    private String logMessage;
}
