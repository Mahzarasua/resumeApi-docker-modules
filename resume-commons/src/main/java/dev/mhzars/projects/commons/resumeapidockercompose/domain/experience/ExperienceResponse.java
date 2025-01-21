package dev.mhzars.projects.commons.resumeapidockercompose.domain.experience;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExperienceResponse {
    @JsonIgnoreProperties(value = "resumeId")
    private List<ExperienceDomain> experienceList;
}
