package dev.mhzars.projects.commons.resumeapidockercompose.controller;

import static dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils.RESUME_ID;
import static dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils.manufacturedPojo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.service.ExperienceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

@Slf4j
class CommonExperienceControllerTest {
    private static CommonExperienceController controller;

    @BeforeEach
    void init() throws JsonProcessingException {
        ExperienceResponse response = manufacturedPojo(ExperienceResponse.class);
        GenericDeleteResponse deleteResponse = manufacturedPojo(GenericDeleteResponse.class);
        ExperienceService service = Mockito.mock(ExperienceService.class);

        Mockito.doReturn(response).when(service).getListbyResumeId(ArgumentMatchers.anyString());
        Mockito.doReturn(response).when(service).saveList(ArgumentMatchers.any());
        Mockito.doReturn(deleteResponse)
                .when(service)
                .deleteRecordbyId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        Mockito.when(service.deleteRecordsbyResumeId(ArgumentMatchers.anyString()))
                .thenReturn(deleteResponse);

        controller = new CommonExperienceController(service);
    }

    @Test
    void givenValidResumeId_whenGetListbyResumeId_thenSuccess() throws JsonProcessingException {
        ExperienceResponse response = controller.getListbyResumeId(RESUME_ID);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void givenValidRequest_whenCreateList_thenSuccess() throws JsonProcessingException {
        ExperienceRequest request = manufacturedPojo(ExperienceRequest.class);
        ExperienceResponse response = controller.saveList(request);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void givenValidResumeId_whenDeleteList_thenSuccess() {
        GenericDeleteResponse response = controller.deleteRecords(RESUME_ID, null);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void givenValidResumeIdandId_whenDeleteList_thenSuccess() {
        GenericDeleteResponse response = controller.deleteRecords(RESUME_ID, "null");
        log.info("Response: {}", response);
        assertNotNull(response);
    }
}
