package dev.mhzars.projects.postgres.resumeapidockercompose.service;

import dev.mhzars.projects.postgres.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.experience.ExperienceRequest;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.experience.ExperienceResponse;

import java.util.UUID;

public interface ExperienceService {
    ExperienceResponse getListbyResumeId(UUID resumeId);

    ExperienceResponse saveList(ExperienceRequest request);

    GenericDeleteResponse deleteRecordsbyResumeId(UUID resumeId);

    GenericDeleteResponse deleteRecordbyId(UUID resumeId, UUID id);
}
