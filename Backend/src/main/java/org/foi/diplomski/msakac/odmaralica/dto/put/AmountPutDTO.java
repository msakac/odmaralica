package org.foi.diplomski.msakac.odmaralica.dto.put;

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
public class AmountPutDTO {
    @NotNull(message = "Id cannot be empty")
    private Long id;
    @NotEmpty(message = "Amount cannot be empty")
    private String amount;
    @NotEmpty(message = "Currency cannot be empty")
    private String currency;
}