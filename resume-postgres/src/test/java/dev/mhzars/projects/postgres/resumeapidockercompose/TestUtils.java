package dev.mhzars.projects.postgres.resumeapidockercompose;

import static dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils.generateUniqueId;

import dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.resume.ResumeRequest;

public class TestUtils extends CommonTestUtils {
    public static void setChildTables(ResumeRequest request) {
        request.getEducationList().forEach(r -> r.setId(generateUniqueId().toString()));
        request.getExperienceList().forEach(r -> r.setId(generateUniqueId().toString()));
        request.getSkillList().forEach(r -> r.setId(generateUniqueId().toString()));
    }
}
