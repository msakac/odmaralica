package org.foi.diplomski.msakac.odmaralica.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CreateResponseDTO<Entity> {
    private Entity data;
    private HttpStatus status;
    private String message;

    public CreateResponseDTO(Entity data, HttpStatus status) {
        this.data = data;
        this.status = status;
        this.message = null;
    }

    public CreateResponseDTO(HttpStatus status, String message) {
        this.data = null;
        this.status = status;
        this.message = message;
    }
}
