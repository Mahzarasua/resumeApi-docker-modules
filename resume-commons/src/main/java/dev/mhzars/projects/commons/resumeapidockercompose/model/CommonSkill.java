package dev.mhzars.projects.commons.resumeapidockercompose.model;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class CommonSkill {
    private String name;
    private int percentage;
    private String type;
    private LocalDateTime creationDate;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommonSkill that = (CommonSkill) o;
        return percentage == that.percentage
                && Objects.equals(name, that.name)
                && Objects.equals(type, that.type)
                && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, percentage, type, creationDate);
    }
}
