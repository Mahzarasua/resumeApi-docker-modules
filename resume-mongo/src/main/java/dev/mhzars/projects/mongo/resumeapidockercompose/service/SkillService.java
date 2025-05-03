package dev.mhzars.projects.mongo.resumeapidockercompose.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.skill.SkillRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.skill.SkillResponse;

public interface SkillService {
    SkillResponse getListbyResumeId(String resumeId) throws JsonProcessingException;

    SkillResponse saveList(SkillRequest request) throws JsonProcessingException;

    GenericDeleteResponse deleteRecordsbyResumeId(String resumeId);

    GenericDeleteResponse deleteRecordbyId(String resumeId, String id);
}
