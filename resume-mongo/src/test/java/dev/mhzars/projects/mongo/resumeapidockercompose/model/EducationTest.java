package dev.mhzars.projects.mongo.resumeapidockercompose.model;

import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.manufacturedPojo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Slf4j
class EducationTest {
    private static Education r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(Education.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        Education tmp = manufacturedPojo(Education.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }
}
