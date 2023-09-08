package dev.mhzars.projects.resume.resumeapidockercompose.service;

import dev.mhzars.projects.resume.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.education.EducationRequest;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.education.EducationResponse;

public interface EducationService {
    EducationResponse getListbyResumeId(String resumeId);

    EducationResponse saveList(EducationRequest request);

    GenericDeleteResponse deleteRecordsbyResumeId(String resumeId);

    GenericDeleteResponse deleteRecordbyId(String resumeId, String id);
}
