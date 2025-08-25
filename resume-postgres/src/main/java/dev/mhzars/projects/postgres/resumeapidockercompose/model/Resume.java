package dev.mhzars.projects.postgres.resumeapidockercompose.model;

import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonResume;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Entity
@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Resume extends CommonResume {
    @Id @GeneratedValue private UUID id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Education> educationList;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<Experience> experienceList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Skill> skillList;

    @PrePersist
    public void setCreationDate() {
        this.setCreationDate(LocalDateTime.now());
        prepareChildTables();
    }

    @PreUpdate
    public void prepareChildTables() {
        if (educationList != null) educationList.forEach(e -> e.setResume(this));
        if (experienceList != null) experienceList.forEach(e -> e.setResume(this));
        if (skillList != null) skillList.forEach(e -> e.setResume(this));
    }

    @Override
    public String toString() {
        return "{"
                + "id="
                + id
                + ", firstName="
                + getFirstName()
                + // Access CommonResume fields directly
                ", lastName="
                + getLastName()
                + ", title="
                + getTitle()
                + ", city="
                + getCity()
                + ", state="
                + getState()
                + ", country="
                + getCountry()
                + ", email="
                + getEmail()
                + ", phone="
                + getPhone()
                + ", summary="
                + getSummary()
                + ", creationDate="
                + getCreationDate()
                + ", educationList="
                + educationList
                + ", experienceList="
                + experienceList
                + ", skillList="
                + skillList
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Resume resume = (Resume) o;
        return Objects.equals(id, resume.id)
                && Objects.equals(educationList, resume.educationList)
                && Objects.equals(experienceList, resume.experienceList)
                && Objects.equals(skillList, resume.skillList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, educationList, experienceList, skillList);
    }
}
