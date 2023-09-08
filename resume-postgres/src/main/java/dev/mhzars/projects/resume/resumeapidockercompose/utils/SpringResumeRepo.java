package dev.mhzars.projects.resume.resumeapidockercompose.utils;

import dev.mhzars.projects.resume.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.resume.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.resume.resumeapidockercompose.repository.ResumeRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SpringResumeRepo {

    private static final String RESUME_ID_NOT_FOUND = "Resume with id %s was not found";
    private final ResumeRepository resumeRepo;

    public SpringResumeRepo(ResumeRepository resumeRepo) {
        this.resumeRepo = resumeRepo;
    }

    public Resume checkResumeId(UUID id) {
        return resumeRepo.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(String.format(RESUME_ID_NOT_FOUND, id)));
    }
}
