package dev.mhzars.projects.postgres.resumeapidockercompose.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import dev.mhzars.projects.commons.resumeapidockercompose.validator.CommonResumeValidator;
import org.junit.jupiter.api.Test;

@SpringBootTest
class ConfigBeansTest {
    @Autowired private RestTemplate restTemplate;

    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private CommonResumeValidator commonResumeValidator;

    @Test
    void testRestTemplateBean() {
        assertNotNull(restTemplate);
    }

    @Test
    void testPasswordEncoderBean() {
        assertNotNull(passwordEncoder);
    }

    @Test
    void testCommonResumeValidatorBean() {
        assertNotNull(commonResumeValidator);
    }
}
