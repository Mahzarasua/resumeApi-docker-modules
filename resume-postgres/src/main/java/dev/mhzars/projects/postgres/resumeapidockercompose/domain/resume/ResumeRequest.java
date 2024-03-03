package dev.mhzars.projects.postgres.resumeapidockercompose.domain.resume;

import dev.mhzars.projects.postgres.resumeapidockercompose.domain.education.EducationDomain;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.experience.ExperienceDomain;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.skill.SkillDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "ResumeRequest")
public class ResumeRequest {
    private UUID id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String title;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotBlank
    @Email(regexp = "^[\\p{L}\\p{N}\\._%+-]+@[\\p{L}\\p{N}\\.\\-]+\\.[\\p{L}]{2,}$")
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String summary;

    private LocalDateTime creationDate;
    private List<SkillDomain> skillList;
    private List<EducationDomain> educationList;
    private List<ExperienceDomain> experienceList;
}
