package org.foi.diplomski.msakac.odmaralica.dto.post;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogPostDTO {
    private Long userId;
    @NotNull(message = "Activity type ID cannot be empty")
    private Long activityTypeId;
    @NotEmpty(message = "Log message cannot be empty")
    private String logMessage;
    @NotEmpty(message = "Created at cannot be empty")
    private Timestamp createdAt;
    @NotEmpty(message = "HTTP method cannot be empty")
    private String httpMethod;
    @NotEmpty(message = "Endpoint cannot be empty")
    private String endpoint;
    @NotEmpty(message = "Status code cannot be empty")
    private String statusCode;
    @NotEmpty(message = "IP address cannot be empty")
    private String ipAddress;
    @NotEmpty(message = "Response time cannot be empty")
    private String responseTime;
}
