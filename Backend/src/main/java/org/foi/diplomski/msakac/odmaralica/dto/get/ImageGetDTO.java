package org.foi.diplomski.msakac.odmaralica.dto.get;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ImageGetDTO {
    Long id;
    Long userId;
    Long accommodationUnitId;
    Long residenceId;
    Long createdBy;
}
