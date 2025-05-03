package dev.mhzars.projects.mongo.resumeapidockercompose;

import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.generateUniqueId;

import dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.resume.ResumeRequest;

public class TestUtils extends CommonTestUtils {
    public static void setChildTables(ResumeRequest request) {
        request.getEducationList().forEach(r -> r.setId(generateUniqueId().toString()));
        request.getExperienceList().forEach(r -> r.setId(generateUniqueId().toString()));
        request.getSkillList().forEach(r -> r.setId(generateUniqueId().toString()));
    }
}
