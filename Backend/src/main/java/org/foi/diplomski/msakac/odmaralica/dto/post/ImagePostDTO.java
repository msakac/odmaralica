package org.foi.diplomski.msakac.odmaralica.dto.post;

import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ImagePostDTO {
    @NotNull(message="Data cannot be empty")
    private MultipartFile data;
    
    private String name;

    private Long userId;

    private Long accommodationUnitId;

    private Long residenceId;

    private Long createdBy;

    private Timestamp createdAt;
}
