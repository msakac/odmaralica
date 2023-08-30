package org.foi.diplomski.msakac.odmaralica.dto.get;

import java.sql.Timestamp;

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
