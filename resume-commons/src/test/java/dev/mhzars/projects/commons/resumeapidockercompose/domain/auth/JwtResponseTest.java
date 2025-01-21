package dev.mhzars.projects.commons.resumeapidockercompose.domain.auth;

import static dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils.manufacturedPojo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Slf4j
class JwtResponseTest {
    private static JwtResponse r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(JwtResponse.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        JwtResponse tmp = manufacturedPojo(JwtResponse.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }
}
