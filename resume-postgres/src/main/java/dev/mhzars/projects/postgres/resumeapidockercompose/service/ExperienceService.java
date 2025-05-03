package dev.mhzars.projects.postgres.resumeapidockercompose.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceResponse;

public interface ExperienceService {
    ExperienceResponse getListbyResumeId(String resumeId) throws JsonProcessingException;

    ExperienceResponse saveList(ExperienceRequest request) throws JsonProcessingException;

    GenericDeleteResponse deleteRecordsbyResumeId(String resumeId);

    GenericDeleteResponse deleteRecordbyId(String resumeId, String id);
}
