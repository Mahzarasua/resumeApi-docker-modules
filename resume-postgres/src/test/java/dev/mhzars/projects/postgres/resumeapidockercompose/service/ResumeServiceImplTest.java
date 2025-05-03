package dev.mhzars.projects.postgres.resumeapidockercompose.service;

import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.RESUME_ID;
import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedPojo;
import static dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils.generateUniqueId;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.ResumeIdResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.ResumeResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.commons.resumeapidockercompose.validator.CommonResumeValidator;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.resume.ResumeRequest;
import dev.mhzars.projects.postgres.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.postgres.resumeapidockercompose.repository.ResumeRepository;
import dev.mhzars.projects.postgres.resumeapidockercompose.validator.ResumeValidator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

@Slf4j
class ResumeServiceImplTest {
    private static ResumeService service;

    private static Resume resume;

    private static ResumeRequest resumeRequest;

    @BeforeAll
    static void start() {
        resume = manufacturedPojo(Resume.class);
        resumeRequest = manufacturedPojo(ResumeRequest.class);
    }

    @BeforeEach
    void init() {
        Optional<Resume> optionalResponse = Optional.ofNullable(resume);
        List<Resume> responseList = Collections.singletonList(resume);
        ResumeResponse resumeResponse = manufacturedPojo(ResumeResponse.class);
        List<ResumeResponse> resumeResponseList = Collections.singletonList(resumeResponse);

        ResumeRepository repository = Mockito.mock(ResumeRepository.class);
        ResumeValidator validator = Mockito.mock(ResumeValidator.class);
        CommonResumeValidator commonResumeValidator = Mockito.mock(CommonResumeValidator.class);

        Mockito.doReturn(responseList).when(repository).findAll();
        Mockito.doReturn(optionalResponse).when(repository).findById(ArgumentMatchers.any());
        Mockito.doReturn(resume).when(repository).save(ArgumentMatchers.any());
        Mockito.doNothing().when(repository).deleteById(ArgumentMatchers.any());
        Mockito.doReturn(optionalResponse)
                .when(repository)
                .findFirstByFirstName(ArgumentMatchers.anyString());
        Mockito.doThrow(new CustomNotFoundException("Not found"))
                .when(repository)
                .findFirstByFirstName(RESUME_ID);

        Mockito.doNothing().when(validator).validate(ArgumentMatchers.any());
        Mockito.doNothing().when(commonResumeValidator).validate(ArgumentMatchers.any());

        service = new ResumeServiceImpl(repository, validator, commonResumeValidator);
    }

    @Test
    void getAllResumes() throws JsonProcessingException {
        List<ResumeResponse> response = service.getAllResumes();
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void getResumeById() {
        ResumeResponse response = service.getResumeById(generateUniqueId().toString());
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void saveResume() {
        ResumeIdResponse response = service.saveResume(resumeRequest);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void saveResume_nullCreationDate() {
        resumeRequest.setCreationDate(null);
        ResumeIdResponse response = service.saveResume(resumeRequest);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void testSaveResume() {
        ResumeIdResponse response =
                service.saveResume(resumeRequest, generateUniqueId().toString());
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void testSaveResume_noCreationDate() {
        resumeRequest.setCreationDate(null);
        resume.setCreationDate(null);
        ResumeIdResponse response =
                service.saveResume(resumeRequest, generateUniqueId().toString());
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void deleteResumeById() {
        ResumeIdResponse response = service.deleteResumeById(generateUniqueId().toString());
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void getResumeByFirstName() {
        ResumeResponse response = service.getResumeByFirstName("Miguel");
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    // Negative
    @Test
    void getResumeByFirstName_Negative() {
        assertThrows(CustomNotFoundException.class, () -> service.getResumeByFirstName(RESUME_ID));
    }
}
