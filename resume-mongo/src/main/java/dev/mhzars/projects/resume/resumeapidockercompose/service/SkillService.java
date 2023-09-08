package dev.mhzars.projects.resume.resumeapidockercompose.service;

import dev.mhzars.projects.resume.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.skill.SkillRequest;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.skill.SkillResponse;

public interface SkillService {
    SkillResponse getListbyResumeId(String resumeId);

    SkillResponse saveList(SkillRequest request);

    GenericDeleteResponse deleteRecordsbyResumeId(String resumeId);

    GenericDeleteResponse deleteRecordbyId(String resumeId, String id);
}
