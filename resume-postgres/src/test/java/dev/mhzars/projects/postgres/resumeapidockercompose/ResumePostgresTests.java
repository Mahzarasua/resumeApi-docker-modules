package dev.mhzars.projects.postgres.resumeapidockercompose;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import org.junit.jupiter.api.Test;

@SpringBootTest
@ContextConfiguration(classes = ResumePostgres.class)
class ResumePostgresTests {

    @Test
    void contextLoads() {
        assertTrue(true);
    }
}
