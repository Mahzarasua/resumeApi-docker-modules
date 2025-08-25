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
class CommonEducationTest {
    private static CommonEducation r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(CommonEducation.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        CommonEducation tmp = manufacturedPojo(CommonEducation.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

    @Test
    void testEquals() {
        CommonEducation tmp = manufacturedPojo(CommonEducation.class);
        assertNotEquals(r, tmp);
        assertNotEquals(null, r);
        CommonEducation eq =
                new CommonEducation(
                        r.getName(),
                        r.getCareer(),
                        r.getDegree(),
                        r.getStartDate(),
                        r.getEndDate(),
                        r.getCreationDate());
        assertEquals(r, eq);

        CommonExperience exp = manufacturedPojo(CommonExperience.class);
        assertNotEquals(r, exp);
    }
}
