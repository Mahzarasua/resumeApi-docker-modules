package dev.mhzars.projects.mongo.resumeapidockercompose.service;

import dev.mhzars.projects.mongo.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.skill.SkillRequest;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.skill.SkillResponse;

public interface SkillService {
    SkillResponse getListbyResumeId(String resumeId);

    SkillResponse saveList(SkillRequest request);

    GenericDeleteResponse deleteRecordsbyResumeId(String resumeId);

    GenericDeleteResponse deleteRecordbyId(String resumeId, String id);
}
