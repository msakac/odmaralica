package org.foi.diplomski.msakac.odmaralica.security.dto;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CreateResponseDTO<Entity> {
    private Entity data;
    private HttpStatus status;
}
