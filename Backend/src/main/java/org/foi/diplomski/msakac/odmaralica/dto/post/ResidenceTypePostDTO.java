package org.foi.diplomski.msakac.odmaralica.dto.post;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ResidenceTypePostDTO {
    @NotEmpty(message = "Name cannot be empty")
    private String name;
}
