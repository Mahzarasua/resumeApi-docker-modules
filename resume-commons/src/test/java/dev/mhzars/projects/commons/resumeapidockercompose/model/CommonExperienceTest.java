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
class CommonExperienceTest {
    private static CommonExperience r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(CommonExperience.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        CommonExperience tmp = manufacturedPojo(CommonExperience.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

    @Test
    void testEquals() {
        CommonExperience tmp = manufacturedPojo(CommonExperience.class);
        assertNotEquals(r, tmp);
        assertNotEquals(null, r);
        CommonExperience eq =
                new CommonExperience(
                        r.getTitle(),
                        r.getCompany(),
                        r.isCurrentJob(),
                        r.getDescription(),
                        r.getStartDate(),
                        r.getEndDate(),
                        r.getCreationDate());
        assertEquals(r, eq);
        CommonEducation exp = manufacturedPojo(CommonEducation.class);
        assertNotEquals(r, exp);
    }
}
