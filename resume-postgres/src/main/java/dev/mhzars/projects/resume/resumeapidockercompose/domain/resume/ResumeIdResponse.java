package dev.mhzars.projects.resume.resumeapidockercompose.domain.resume;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "ResumeIdResponse")
public class ResumeIdResponse {
    private UUID resumeId;
}
