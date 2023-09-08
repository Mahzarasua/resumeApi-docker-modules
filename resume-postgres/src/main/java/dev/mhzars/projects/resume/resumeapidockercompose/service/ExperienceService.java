package dev.mhzars.projects.resume.resumeapidockercompose.service;

import dev.mhzars.projects.resume.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.experience.ExperienceRequest;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.experience.ExperienceResponse;

import java.util.UUID;

public interface ExperienceService {
    ExperienceResponse getListbyResumeId(UUID resumeId);

    ExperienceResponse saveList(ExperienceRequest request);

    GenericDeleteResponse deleteRecordsbyResumeId(UUID resumeId);

    GenericDeleteResponse deleteRecordbyId(UUID resumeId, UUID id);
}
