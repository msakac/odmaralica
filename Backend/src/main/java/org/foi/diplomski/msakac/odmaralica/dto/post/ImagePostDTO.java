package org.foi.diplomski.msakac.odmaralica.dto.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ImagePostDTO {
    @NotNull(message = "Data cannot be empty")
    private MultipartFile data;

    private String name;

    private Long userId;

    private Long accommodationUnitId;

    private Long residenceId;

    private Long createdBy;

    private Timestamp createdAt;
}
