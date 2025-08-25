package dev.mhzars.projects.commons.resumeapidockercompose.domain.skill;

import dev.mhzars.projects.commons.resumeapidockercompose.podam.GenerateUniqueIdStrategy;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.jemos.podam.common.PodamStrategyValue;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillDomain {
    @PodamStrategyValue(GenerateUniqueIdStrategy.class)
    private String resumeId;

    @PodamStrategyValue(GenerateUniqueIdStrategy.class)
    private String id;

    private String name;

    @Min(0)
    @Max(99)
    private int percentage;

    private String type;
    private LocalDateTime creationDate;
}
