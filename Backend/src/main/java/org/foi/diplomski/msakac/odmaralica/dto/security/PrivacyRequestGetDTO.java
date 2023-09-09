package org.foi.diplomski.msakac.odmaralica.dto.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PrivacyRequestGetDTO {
    private Long id;
    private Timestamp createdAt;
    private UserGetDTO user;
    private String reason;
    private boolean accepted;
    private boolean revoked;
    private UserGetDTO acceptedBy;
}
