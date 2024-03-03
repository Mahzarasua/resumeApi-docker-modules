package dev.mhzars.projects.mongo.resumeapidockercompose.service;

import dev.mhzars.projects.mongo.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.experience.ExperienceRequest;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.experience.ExperienceResponse;

public interface ExperienceService {
    ExperienceResponse getListbyResumeId(String resumeId);

    ExperienceResponse saveList(ExperienceRequest request);

    GenericDeleteResponse deleteRecordsbyResumeId(String resumeId);

    GenericDeleteResponse deleteRecordbyId(String resumeId, String id);
}
