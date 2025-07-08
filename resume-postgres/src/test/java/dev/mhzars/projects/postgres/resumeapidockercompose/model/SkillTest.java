package dev.mhzars.projects.postgres.resumeapidockercompose.model;

import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedCustomPojo;
import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedPojo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Slf4j
class SkillTest {
    private static Skill r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(Skill.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        Skill tmp = manufacturedPojo(Skill.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

    @Test
    void testSetCreationDate() {
        LocalDateTime beforeCall = LocalDateTime.now();
        r.setCreationDate();
        LocalDateTime afterCall = LocalDateTime.now();
        LocalDateTime actualCreationDate = r.getCreationDate();

        assertNotNull(
                actualCreationDate,
                "Creation date should not be null after calling setCreationDate.");
        assertTrue(
                actualCreationDate.isAfter(beforeCall) || actualCreationDate.isEqual(beforeCall),
                "Creation date should not be before the start of the test window.");
        assertTrue(
                actualCreationDate.isBefore(afterCall) || actualCreationDate.isEqual(afterCall),
                "Creation date should not be after the end of the test window.");

        assertEquals(
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                r.getCreationDate().truncatedTo(ChronoUnit.SECONDS),
                "Creation date should be roughly the current time within a second.");
        assertTrue(
                actualCreationDate.isAfter(beforeCall.minus(100, ChronoUnit.MILLIS)),
                "Creation date should not be too far in the past.");
        assertTrue(
                actualCreationDate.isBefore(beforeCall.plus(100, ChronoUnit.MILLIS)),
                "Creation date should not be too far in the future.");
    }

    @Test
    void testPreRemoval() {
        Resume resume = manufacturedCustomPojo(Resume.class);
        List<Skill> list = resume.getSkillList();
        int originalCount = list.size();

        list.get(0).setResume(resume);
        list.get(0).preRemoveSkill();

        assertEquals(originalCount - 1, list.size());
    }
}
