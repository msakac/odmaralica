package org.foi.diplomski.msakac.odmaralica.dto.post;

import javax.validation.constraints.NotEmpty;
import org.foi.diplomski.msakac.odmaralica.dto.IRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AmountPostDTO implements IRequest {
    @NotEmpty(message = "Amount cannot be empty")
	private String amount;
    @NotEmpty(message = "Currency cannot be empty")
	private String currency;
    @Override

    public void placeholder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeholder'");
    }
}
