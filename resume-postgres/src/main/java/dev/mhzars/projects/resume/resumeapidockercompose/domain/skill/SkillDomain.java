package dev.mhzars.projects.resume.resumeapidockercompose.domain.skill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillDomain {
    private UUID id;
    private UUID resumeId;
    private String name;
    private int percentage;
    private String type;
    private LocalDateTime creationDate;
}
