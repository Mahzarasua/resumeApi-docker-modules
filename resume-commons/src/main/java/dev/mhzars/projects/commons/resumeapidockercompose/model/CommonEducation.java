package dev.mhzars.projects.commons.resumeapidockercompose.model;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class CommonEducation {
    private String name;
    private String career;
    private String degree;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime creationDate;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommonEducation that = (CommonEducation) o;
        return Objects.equals(name, that.name)
                && Objects.equals(career, that.career)
                && Objects.equals(degree, that.degree)
                && Objects.equals(startDate, that.startDate)
                && Objects.equals(endDate, that.endDate)
                && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, career, degree, startDate, endDate, creationDate);
    }
}
