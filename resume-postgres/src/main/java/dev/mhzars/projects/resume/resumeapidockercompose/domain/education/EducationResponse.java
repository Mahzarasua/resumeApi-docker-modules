package dev.mhzars.projects.resume.resumeapidockercompose.domain.education;

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
@Schema(description = "EducationResponse")
public class EducationResponse {
    private List<EducationDomain> educationList;
}
