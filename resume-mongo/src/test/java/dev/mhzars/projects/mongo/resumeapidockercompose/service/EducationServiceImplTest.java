package dev.mhzars.projects.mongo.resumeapidockercompose.service;

import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.RESUME_ID;
import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.manufacturedCustomPojo;
import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.manufacturedPojo;
import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.generateUniqueObjectId;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.mhzars.projects.commons.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.education.EducationDomain;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.education.EducationRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.education.EducationResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.mongo.resumeapidockercompose.mapper.CustomMapper;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Education;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.mongo.resumeapidockercompose.repository.ResumeRepository;
import dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringResumeRepo;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

@Slf4j
class EducationServiceImplTest {

    private static EducationService service;
    private static Resume resume;

    @BeforeAll
    static void start() {
        resume = manufacturedPojo(Resume.class);
    }

    @BeforeEach
    void init() {
        List<Education> entityList = new ArrayList<>();
        List<EducationDomain> domainList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Education e = manufacturedCustomPojo(Education.class);
            entityList.add(e);
            EducationDomain d = manufacturedCustomPojo(EducationDomain.class);
            domainList.add(d);
        }
        resume.setEducationList(entityList);

        Resume resume1 = manufacturedPojo(Resume.class);
        resume1.getEducationList().clear();

        ResumeRepository repository = Mockito.mock(ResumeRepository.class);
        SpringResumeRepo checkResume = Mockito.mock(SpringResumeRepo.class);
        CustomMapper mapper = Mockito.mock(CustomMapper.class);

        Mockito.doReturn(resume).when(checkResume).checkResumeId(ArgumentMatchers.anyString());
        Mockito.doReturn(resume1).when(checkResume).checkResumeId(RESUME_ID);
        Mockito.doReturn(resume).when(repository).save(ArgumentMatchers.any());
        Mockito.when(
                        mapper.mapAsList(
                                ArgumentMatchers.anyList(),
                                ArgumentMatchers.eq(EducationDomain.class)))
                .thenReturn(domainList);

        service = new EducationServiceImpl(repository, mapper, checkResume);
    }

    @Test
    void getListbyResumeId() {
        EducationResponse response = service.getListbyResumeId("RESUME_ID");
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void saveList() {
        EducationRequest request = manufacturedPojo(EducationRequest.class);
        request.getEducationList().forEach(e -> e.setId(generateUniqueObjectId().toString()));
        EducationResponse response = service.saveList(request);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void deleteRecordsbyResumeId() {
        GenericDeleteResponse response = service.deleteRecordsbyResumeId("RESUME_ID");
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void deleteRecordbyId() {
        List<Education> educationList = resume.getEducationList();
        String id = educationList.get(0).getId().toString();
        GenericDeleteResponse response = service.deleteRecordbyId(resume.getId().toString(), id);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    // Negative
    @Test
    void getListbyResumeId_Negative() {
        assertThrows(CustomNotFoundException.class, () -> service.getListbyResumeId(RESUME_ID));
    }

    @Test
    void deleteRecordsbyResumeId_Negative() {
        assertThrows(
                CustomNotFoundException.class, () -> service.deleteRecordsbyResumeId(RESUME_ID));
    }

    @Test
    void deleteRecordbyId_Negative() {
        String resumeId = resume.getId().toString();
        String id = generateUniqueObjectId().toString();
        assertThrows(CustomNotFoundException.class, () -> service.deleteRecordbyId(resumeId, id));
    }
}
