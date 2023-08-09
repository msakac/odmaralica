package org.foi.diplomski.msakac.odmaralica.security.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoleRequestDTO {
    @NotEmpty(message = "{role_not_empty}")
	private String role;
}
