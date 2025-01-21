package dev.mhzars.projects.mongo.resumeapidockercompose;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import org.junit.jupiter.api.Test;

@SpringBootTest
@ContextConfiguration(classes = ResumeMongo.class)
class ResumeMongoTest {
    @Test
    void contextLoads() {
        assertTrue(true);
    }
}
