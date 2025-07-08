package dev.mhzars.projects.commons.resumeapidockercompose.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CommonConfigBeansTest {
    static CommonConfigBeans commonConfigBeans;

    @BeforeAll
    static void setup() {
        commonConfigBeans = new CommonConfigBeans();
    }

    @Test
    void testBeansCreation() {
        assertNotNull(commonConfigBeans.getCommonJwtTokenUtil());
        assertNotNull(commonConfigBeans.getCommonResumeValidator());
        assertNotNull(commonConfigBeans.getPasswordEncoder());
        assertNotNull(commonConfigBeans.restTemplate());
    }
}
