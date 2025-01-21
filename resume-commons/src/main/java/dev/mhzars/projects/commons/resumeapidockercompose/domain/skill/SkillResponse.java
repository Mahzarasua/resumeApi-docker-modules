package dev.mhzars.projects.commons.resumeapidockercompose.domain.skill;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "SkillResponse")
public class SkillResponse {
    @JsonIgnoreProperties(value = "resumeId")
    private List<SkillDomain> skillList;
}
