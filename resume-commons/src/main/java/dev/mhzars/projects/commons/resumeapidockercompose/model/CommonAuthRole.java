package dev.mhzars.projects.commons.resumeapidockercompose.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.MappedSuperclass;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@MappedSuperclass
public class CommonAuthRole implements Serializable {
    @Serial private static final long serialVersionUID = 2350147283914722122L;
    private String role;
    private LocalDateTime creationDate;

    @Override
    public String toString() {
        return "role='" + role + '\'' + ", creationDate=" + creationDate;
    }
}
