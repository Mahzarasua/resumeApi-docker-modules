package dev.mhzars.projects.mongo.resumeapidockercompose.service;

import dev.mhzars.projects.mongo.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.education.EducationRequest;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.education.EducationResponse;

public interface EducationService {
    EducationResponse getListbyResumeId(String resumeId);

    EducationResponse saveList(EducationRequest request);

    GenericDeleteResponse deleteRecordsbyResumeId(String resumeId);

    GenericDeleteResponse deleteRecordbyId(String resumeId, String id);
}
