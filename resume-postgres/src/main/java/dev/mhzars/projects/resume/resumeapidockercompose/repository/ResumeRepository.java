package dev.mhzars.projects.resume.resumeapidockercompose.repository;

import dev.mhzars.projects.resume.resumeapidockercompose.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, UUID> {
    Optional<Resume> findFirstByFirstName(String firstName);
}
