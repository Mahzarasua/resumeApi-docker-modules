package dev.mhzars.projects.resume.resumeapidockercompose.domain.education;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationDomain {
    private String resumeId;
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String career;
    @NotBlank
    private String degree;
    @NotBlank
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime creationDate;
}
