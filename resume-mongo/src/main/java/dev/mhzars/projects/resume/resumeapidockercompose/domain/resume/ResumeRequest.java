package dev.mhzars.projects.resume.resumeapidockercompose.domain.resume;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.education.EducationDomain;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.experience.ExperienceDomain;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.skill.SkillDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "ResumeRequest")
public class ResumeRequest {
    private String id;
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

    @JsonIgnoreProperties(value = "resumeId")
    private List<SkillDomain> skillList;
    @JsonIgnoreProperties(value = "resumeId")
    private List<EducationDomain> educationList;
    @JsonIgnoreProperties(value = "resumeId")
    private List<ExperienceDomain> experienceList;
}
