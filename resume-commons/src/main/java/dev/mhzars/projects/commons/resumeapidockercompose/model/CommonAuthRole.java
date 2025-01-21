package dev.mhzars.projects.commons.resumeapidockercompose.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties
public class CommonAuthRole implements Serializable {
    @Serial private static final long serialVersionUID = 2350147283914722122L;
    private String role;
    private LocalDateTime creationDate;
}
