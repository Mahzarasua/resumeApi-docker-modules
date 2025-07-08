package dev.mhzars.projects.commons.resumeapidockercompose.model;

import static dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils.manufacturedPojo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Slf4j
class CommonResumeTest {
    private static CommonResume r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(CommonResume.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        CommonResume tmp = manufacturedPojo(CommonResume.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        assertNotEquals(r, tmp);
        assertNotEquals(null, r);
        CommonResume eq =
                new CommonResume(
                        r.getFirstName(),
                        r.getLastName(),
                        r.getTitle(),
                        r.getCity(),
                        r.getState(),
                        r.getCountry(),
                        r.getEmail(),
                        r.getPhone(),
                        r.getSummary(),
                        r.getCreationDate());
        assertEquals(r, eq);
        CommonEducation exp = manufacturedPojo(CommonEducation.class);
        assertFalse(r.equals(exp));
        log.info("{}", tmp);
    }
}
