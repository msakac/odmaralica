package org.foi.diplomski.msakac.odmaralica.dto.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class EncryptedLogGetDTO {
    private String id;
    private String user;
    private String activityType;
    private String logMessage;
    private String createdAt;
    private String httpMethod;
    private String endpoint;
    private String statusCode;
    private String ipAddress;
    private String responseTime;
}
