package org.foi.diplomski.msakac.odmaralica.dto.put;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LogPutDTO {
    @NotNull(message = "Id cannot be empty")
    private Long id;
    @NotNull(message = "User ID cannot be empty")
    private Long userId;
    @NotNull(message = "Activity type ID cannot be empty")
    private Long activityTypeId;
    @NotEmpty(message = "Log message cannot be empty")
    private String logMessage;
}
