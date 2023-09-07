package org.foi.diplomski.msakac.odmaralica.dto.custom;

import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
