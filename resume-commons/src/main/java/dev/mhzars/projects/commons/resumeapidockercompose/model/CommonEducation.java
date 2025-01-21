package dev.mhzars.projects.commons.resumeapidockercompose.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonEducation {

    private String name;
    private String career;
    private String degree;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime creationDate;
}
