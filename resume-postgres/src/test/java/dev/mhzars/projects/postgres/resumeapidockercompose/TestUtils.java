package dev.mhzars.projects.postgres.resumeapidockercompose;

import static dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils.generateUniqueId;

import dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.CommonResumeRequest;

public class TestUtils extends CommonTestUtils {
    public static void setChildTables(CommonResumeRequest request) {
        request.getEducationList().forEach(r -> r.setId(generateUniqueId().toString()));
        request.getExperienceList().forEach(r -> r.setId(generateUniqueId().toString()));
        request.getSkillList().forEach(r -> r.setId(generateUniqueId().toString()));
    }
}
