package org.foi.diplomski.msakac.odmaralica.dto.put;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReviewPutDTO {
    @NotNull(message = "Id cannot be empty")
    private Long id;
    @NotNull(message = "Residence ID cannot be empty")
    private Long residenceId;
    @NotNull(message = "User ID cannot be empty")
    private Long userId;
    @DecimalMin(value = "1", inclusive = false, message = "Amount must be greater than 1")
    @DecimalMax(value = "6", inclusive = false, message = "Amount must be less than 5")
    @NotNull(message = "Grade cannot be empty")
    private double grade;
    private String message;
}
