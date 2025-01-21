package dev.mhzars.projects.mongo.resumeapidockercompose.model;

import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.manufacturedPojo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Slf4j
class AuthRoleTest {
    private static AuthRole r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(AuthRole.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        AuthRole tmp = manufacturedPojo(AuthRole.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

    @Test
    void test_Constructor() {
        AuthRole tmp = new AuthRole("role", LocalDateTime.now());
        assertNotNull(tmp);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }
}
