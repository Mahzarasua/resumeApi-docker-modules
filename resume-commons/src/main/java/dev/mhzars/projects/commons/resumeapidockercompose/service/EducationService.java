package dev.mhzars.projects.commons.resumeapidockercompose.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.education.EducationRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.education.EducationResponse;

public interface EducationService {
    EducationResponse getListbyResumeId(String resumeId) throws JsonProcessingException;

    EducationResponse saveList(EducationRequest request) throws JsonProcessingException;

    GenericDeleteResponse deleteRecordsbyResumeId(String resumeId);

    GenericDeleteResponse deleteRecordbyId(String resumeId, String id);
}
