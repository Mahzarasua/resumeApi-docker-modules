package dev.mhzars.projects.postgres.resumeapidockercompose.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonExperience;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience extends CommonExperience {
    @Id @GeneratedValue private UUID id;

    @ManyToOne @ToString.Exclude @JsonIgnore private Resume resume;

    @PrePersist
    public void setCreationDate() {
        this.setCreationDate(LocalDateTime.now());
    }

    @PreRemove
    public void preRemoveWorkExp() {
        resume.getExperienceList().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Experience that = (Experience) o;
        return Objects.equals(id, that.id) && Objects.equals(resume, that.resume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, resume);
    }
}
