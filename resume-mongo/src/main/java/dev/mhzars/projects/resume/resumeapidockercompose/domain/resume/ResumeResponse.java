package dev.mhzars.projects.resume.resumeapidockercompose.domain.resume;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.education.EducationDomain;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.experience.ExperienceDomain;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.skill.SkillDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "ResumeResponse")
public class ResumeResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String title;
    private String city;
    private String state;
    private String country;
    private String email;
    private String phone;
    private String summary;
    private LocalDateTime creationDate;

    @JsonIgnoreProperties(value = "resumeId")
    private List<SkillDomain> skillList;
    @JsonIgnoreProperties(value = "resumeId")
    private List<EducationDomain> educationList;
    @JsonIgnoreProperties(value = "resumeId")
    private List<ExperienceDomain> experienceList;

}
