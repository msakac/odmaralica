package org.foi.diplomski.msakac.odmaralica.dto.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LogPostDTO {
    @NotNull(message = "User ID cannot be empty")
    private Long userId;
    @NotNull(message = "Activity type ID cannot be empty")
    private Long activityTypeId;
    @NotEmpty(message = "Log message cannot be empty")
    private String logMessage;
}
