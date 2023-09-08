package dev.mhzars.projects.resume.resumeapidockercompose.domain.skill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillDomain {
    private String resumeId;
    private String id;
    private String name;
    private int percentage;
    private String type;
    private LocalDateTime creationDate;
}
