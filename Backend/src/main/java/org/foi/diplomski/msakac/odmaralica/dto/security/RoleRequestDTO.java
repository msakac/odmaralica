package org.foi.diplomski.msakac.odmaralica.dto.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoleRequestDTO {
    @NotEmpty(message = "{role_not_empty}")
    private String role;
}
