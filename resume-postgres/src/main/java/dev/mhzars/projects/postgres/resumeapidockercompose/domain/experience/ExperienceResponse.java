package dev.mhzars.projects.postgres.resumeapidockercompose.domain.experience;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExperienceResponse {
    private List<ExperienceDomain> experienceList;
}
