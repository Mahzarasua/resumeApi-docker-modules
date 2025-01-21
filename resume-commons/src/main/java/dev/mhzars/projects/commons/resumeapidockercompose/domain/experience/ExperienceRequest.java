package dev.mhzars.projects.commons.resumeapidockercompose.domain.experience;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExperienceRequest {
    private List<ExperienceDomain> experienceList;
}
