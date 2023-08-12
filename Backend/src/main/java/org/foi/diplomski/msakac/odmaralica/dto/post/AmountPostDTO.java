package org.foi.diplomski.msakac.odmaralica.dto.post;

import javax.validation.constraints.DecimalMin;
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
public class AmountPostDTO{
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    @NotNull(message = "Amount cannot be empty")
	private double amount;
    @NotEmpty(message = "Currency cannot be empty")
	private String currency;
}
