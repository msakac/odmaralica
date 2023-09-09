package org.foi.diplomski.msakac.odmaralica.dto.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PrivacyRequestPutDTO {
    @NotNull(message = "Id cannot be empty")
    private Long id;

    private Timestamp createdAt;

    @NotNull(message = "User ID cannot be empty")
    private Long userId;

    private String reason;

    @NotNull(message = "Is accepted cannot be empty")
    private boolean accepted;

    @NotNull(message = "Is revoked cannot be empty")
    private boolean revoked;

    private Long acceptedById;
}
