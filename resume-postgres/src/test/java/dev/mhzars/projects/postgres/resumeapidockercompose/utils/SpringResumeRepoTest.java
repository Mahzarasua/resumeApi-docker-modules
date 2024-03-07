package dev.mhzars.projects.postgres.resumeapidockercompose.utils;

import dev.mhzars.projects.postgres.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.postgres.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.postgres.resumeapidockercompose.repository.ResumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Optional;

import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedCustomPojo;
import static dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils.generateUniqueObjectId;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class SpringResumeRepoTest {
    private final String objectId = String.valueOf(generateUniqueObjectId());
    private SpringResumeRepo springResumeRepo;

    @BeforeEach
    void init() {
        ResumeRepository resumeRepo = Mockito.mock(ResumeRepository.class);
        Resume resume = manufacturedCustomPojo(Resume.class);
        Optional<Resume> optionalResume = Optional.ofNullable(resume);
        Optional<Resume> emptyResume = Optional.empty();

        Mockito.doReturn(optionalResume)
                .when(resumeRepo).findById(ArgumentMatchers.anyString());
        Mockito.doReturn(emptyResume)
                .when(resumeRepo).findById(objectId);

        springResumeRepo = new SpringResumeRepo(resumeRepo);
    }

    @Test
    void checkResumeId() {
        springResumeRepo.checkResumeId(generateUniqueObjectId().toString());
    }

    @Test
    void checkResumeId_throwsException() {
        String objectIdString = objectId;
        Exception e = assertThrows(CustomNotFoundException.class, () -> springResumeRepo.checkResumeId(objectIdString));

        assertTrue(e.getMessage().contains("Resume with id"));
        log.info("{}", e.getMessage());
    }
}