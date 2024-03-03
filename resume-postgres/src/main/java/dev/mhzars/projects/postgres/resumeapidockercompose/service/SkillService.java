package dev.mhzars.projects.postgres.resumeapidockercompose.service;

import dev.mhzars.projects.postgres.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.skill.SkillRequest;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.skill.SkillResponse;

import java.util.UUID;

public interface SkillService {
    SkillResponse getListbyResumeId(UUID resumeId);

    SkillResponse saveList(SkillRequest request);

    GenericDeleteResponse deleteRecordsbyResumeId(UUID resumeId);

    GenericDeleteResponse deleteRecordbyId(UUID resumeId, UUID id);
}
