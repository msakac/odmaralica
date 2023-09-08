package org.foi.diplomski.msakac.odmaralica.dto.get;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LogGetDTO {
    private Long id;
    private UserGetDTO user;
    private ActivityTypeGetDTO activityType;
    private String logMessage;
    private Timestamp createdAt;
    private String httpMethod;
    private String endpoint;
    private String statusCode;
    private String ipAddress;
    private String responseTime;
}
