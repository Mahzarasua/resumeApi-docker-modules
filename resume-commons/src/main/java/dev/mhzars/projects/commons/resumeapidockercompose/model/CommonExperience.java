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
public class CommonExperience {
    private String title;
    private String company;
    private boolean currentJob;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime creationDate;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommonExperience that = (CommonExperience) o;
        return currentJob == that.currentJob
                && Objects.equals(title, that.title)
                && Objects.equals(company, that.company)
                && Objects.equals(description, that.description)
                && Objects.equals(startDate, that.startDate)
                && Objects.equals(endDate, that.endDate)
                && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                title, company, currentJob, description, startDate, endDate, creationDate);
    }
}
