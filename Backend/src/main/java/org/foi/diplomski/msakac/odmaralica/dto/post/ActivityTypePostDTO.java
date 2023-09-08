package org.foi.diplomski.msakac.odmaralica.dto.post;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ActivityTypePostDTO {
    @NotEmpty(message = "Name cannot be empty")
    private String name;
}
