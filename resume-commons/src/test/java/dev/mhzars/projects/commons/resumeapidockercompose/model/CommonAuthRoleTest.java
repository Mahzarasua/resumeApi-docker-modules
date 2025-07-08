package dev.mhzars.projects.commons.resumeapidockercompose.model;

import static dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils.manufacturedPojo;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Slf4j
class CommonAuthRoleTest {
    private static CommonAuthRole r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(CommonAuthRole.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        CommonAuthRole tmp = manufacturedPojo(CommonAuthRole.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

    @Test
    void test_Constructor() {
        CommonAuthRole tmp = new CommonAuthRole("role", LocalDateTime.now());
        assertNotNull(tmp);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }
}
