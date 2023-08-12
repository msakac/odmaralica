package org.foi.diplomski.msakac.odmaralica.dto.put;

import org.foi.diplomski.msakac.odmaralica.dto.IRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AmountPutDto implements IRequest {
	private String amount;
	private String currency;

    @Override
    public void placeholder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeholder'");
    }
}
