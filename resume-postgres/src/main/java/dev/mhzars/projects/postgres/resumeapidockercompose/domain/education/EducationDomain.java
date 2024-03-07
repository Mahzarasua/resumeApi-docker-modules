package dev.mhzars.projects.postgres.resumeapidockercompose.domain.education;

import dev.mhzars.projects.postgres.resumeapidockercompose.podam.DegreeStrategy;
import dev.mhzars.projects.postgres.resumeapidockercompose.podam.GenerateUUIDStrategy;
import dev.mhzars.projects.postgres.resumeapidockercompose.validator.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.jemos.podam.common.PodamStrategyValue;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationDomain {
    @PodamStrategyValue(GenerateUUIDStrategy.class)
    private String resumeId;
    @PodamStrategyValue(GenerateUUIDStrategy.class)
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String career;
    @NotBlank
    @ValueOfEnum(enumClass = EducationDegreeEnum.class)
    @PodamStrategyValue(DegreeStrategy.class)
    private String degree;
    @NotBlank
    @Past
    private LocalDate startDate;
    @FutureOrPresent
    private LocalDate endDate;
    private LocalDateTime creationDate;
}
