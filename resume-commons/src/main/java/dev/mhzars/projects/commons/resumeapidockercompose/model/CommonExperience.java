package dev.mhzars.projects.commons.resumeapidockercompose.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonExperience {

    private String title;
    private String company;
    private boolean currentJob;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime creationDate;
}
