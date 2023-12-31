package dev.mhzars.projects.resume.resumeapidockercompose.domain.skill;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "SkillResponse")
public class SkillResponse {
    private List<SkillDomain> skillList;
}
