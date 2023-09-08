package dev.mhzars.projects.resume.resumeapidockercompose.service;

import dev.mhzars.projects.resume.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.experience.ExperienceRequest;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.experience.ExperienceResponse;

public interface ExperienceService {
    ExperienceResponse getListbyResumeId(String resumeId);

    ExperienceResponse saveList(ExperienceRequest request);

    GenericDeleteResponse deleteRecordsbyResumeId(String resumeId);

    GenericDeleteResponse deleteRecordbyId(String resumeId, String id);
}
