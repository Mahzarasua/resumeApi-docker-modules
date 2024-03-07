package dev.mhzars.projects.postgres.resumeapidockercompose.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedCustomPojo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class EducationTest {
    private static Education r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedCustomPojo(Education.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        Education tmp = manufacturedCustomPojo(Education.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

}