package dev.mhzars.projects.postgres.resumeapidockercompose.domain.resume;

import dev.mhzars.projects.postgres.resumeapidockercompose.domain.education.EducationDomain;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.experience.ExperienceDomain;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.skill.SkillDomain;
import dev.mhzars.projects.postgres.resumeapidockercompose.podam.EmailStrategy;
import dev.mhzars.projects.postgres.resumeapidockercompose.podam.GenerateUUIDStrategy;
import dev.mhzars.projects.postgres.resumeapidockercompose.podam.PhoneStrategy;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.jemos.podam.common.PodamStrategyValue;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "ResumeRequest")
public class ResumeRequest {
    @PodamStrategyValue(GenerateUUIDStrategy.class)
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
    @PodamStrategyValue(EmailStrategy.class)
    @Email(regexp = "^[\\p{L}\\p{N}\\._%+-]+@[\\p{L}\\p{N}\\.\\-]+\\.[\\p{L}]{2,}$")
    private String email;
    @NotBlank
    @PodamStrategyValue(PhoneStrategy.class)
    private String phone;
    @NotBlank
    private String summary;
    private LocalDateTime creationDate;

    private List<SkillDomain> skillList;
    private List<EducationDomain> educationList;
    private List<ExperienceDomain> experienceList;
}
