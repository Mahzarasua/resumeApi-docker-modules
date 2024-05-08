package dev.mhzars.projects.mongo.resumeapidockercompose.domain.resume;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.education.EducationDomain;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.experience.ExperienceDomain;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.skill.SkillDomain;
import dev.mhzars.projects.mongo.resumeapidockercompose.podam.EmailStrategy;
import dev.mhzars.projects.mongo.resumeapidockercompose.podam.GenerateUUIDStrategy;
import dev.mhzars.projects.mongo.resumeapidockercompose.podam.PhoneStrategy;
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

    @JsonIgnoreProperties(value = "resumeId")
    private List<SkillDomain> skillList;
    @JsonIgnoreProperties(value = "resumeId")
    private List<EducationDomain> educationList;
    @JsonIgnoreProperties(value = "resumeId")
    private List<ExperienceDomain> experienceList;
}
