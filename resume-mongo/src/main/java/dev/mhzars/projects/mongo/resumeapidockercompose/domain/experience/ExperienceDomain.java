package dev.mhzars.projects.mongo.resumeapidockercompose.domain.experience;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDomain {
    private String resumeId;
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String company;
    private boolean currentJob;
    @NotBlank
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime creationDate;
}
