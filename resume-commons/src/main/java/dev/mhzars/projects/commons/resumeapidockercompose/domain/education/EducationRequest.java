package dev.mhzars.projects.commons.resumeapidockercompose.domain.education;

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
@Schema(description = "EducationRequest")
public class EducationRequest {
    private List<EducationDomain> educationList;
}
