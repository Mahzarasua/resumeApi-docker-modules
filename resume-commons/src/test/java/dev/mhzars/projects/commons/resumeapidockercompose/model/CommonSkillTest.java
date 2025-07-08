package dev.mhzars.projects.commons.resumeapidockercompose.model;

import static dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils.manufacturedPojo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Slf4j
class CommonSkillTest {
    private static CommonSkill r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(CommonSkill.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        CommonSkill tmp = manufacturedPojo(CommonSkill.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

    @Test
    void testEquals() {
        CommonSkill tmp = manufacturedPojo(CommonSkill.class);
        assertNotEquals(r, tmp);
        assertNotEquals(null, r);
        CommonSkill eq =
                new CommonSkill(r.getName(), r.getPercentage(), r.getType(), r.getCreationDate());
        assertEquals(r, eq);
        CommonEducation exp = manufacturedPojo(CommonEducation.class);
        assertNotEquals(r, exp);
    }
}
