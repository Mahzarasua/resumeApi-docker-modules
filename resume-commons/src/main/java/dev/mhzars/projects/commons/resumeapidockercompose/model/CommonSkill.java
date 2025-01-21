package dev.mhzars.projects.commons.resumeapidockercompose.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonSkill {

    private String name;
    private int percentage;
    private String type;
    private LocalDateTime creationDate;
}
