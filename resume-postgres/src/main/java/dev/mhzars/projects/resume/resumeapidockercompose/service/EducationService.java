package dev.mhzars.projects.resume.resumeapidockercompose.service;

import dev.mhzars.projects.resume.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.education.EducationRequest;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.education.EducationResponse;

import java.util.UUID;

public interface EducationService {
    EducationResponse getListbyResumeId(UUID resumeId);

    EducationResponse saveList(EducationRequest request);

    GenericDeleteResponse deleteRecordsbyResumeId(UUID resumeId);

    GenericDeleteResponse deleteRecordbyId(UUID resumeId, UUID id);
}
