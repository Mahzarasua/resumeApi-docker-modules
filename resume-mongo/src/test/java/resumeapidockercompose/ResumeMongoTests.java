package resumeapidockercompose;

import dev.mhzars.projects.mongo.resumeapidockercompose.ResumeMongo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(classes = ResumeMongo.class)
class ResumeMongoTests {

    @Test
    void contextLoads() {
        assertTrue(true);
    }

}
