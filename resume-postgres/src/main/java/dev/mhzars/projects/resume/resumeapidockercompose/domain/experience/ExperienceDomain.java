package dev.mhzars.projects.resume.resumeapidockercompose.domain.experience;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDomain {
    private UUID id;
    private UUID resumeId;
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
