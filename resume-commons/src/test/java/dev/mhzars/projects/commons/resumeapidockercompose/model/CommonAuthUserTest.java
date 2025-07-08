package dev.mhzars.projects.commons.resumeapidockercompose.model;

import static dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils.manufacturedPojo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Slf4j
class CommonAuthUserTest {

    private static CommonAuthUser r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(CommonAuthUser.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        CommonAuthUser tmp = manufacturedPojo(CommonAuthUser.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

    @Test
    void test_Constructor() {
        CommonAuthRole tmpRole = manufacturedPojo(CommonAuthRole.class);
        CommonAuthUser tmp =
                new CommonAuthUser(
                        "username",
                        "passowrd",
                        true,
                        LocalDateTime.now(),
                        Collections.singletonList(tmpRole));
        assertNotNull(tmp);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }
}
