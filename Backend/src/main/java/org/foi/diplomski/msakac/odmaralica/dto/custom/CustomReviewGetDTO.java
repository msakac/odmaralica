package org.foi.diplomski.msakac.odmaralica.dto.custom;

import lombok.*;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class CustomReviewGetDTO {
    private Long id;
    private UserGetDTO user;
    private double grade;
    private String message;
}
