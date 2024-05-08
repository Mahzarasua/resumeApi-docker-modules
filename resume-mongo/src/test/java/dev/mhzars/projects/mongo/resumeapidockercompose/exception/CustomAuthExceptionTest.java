package dev.mhzars.projects.mongo.resumeapidockercompose.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.manufacturedPojo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class CustomAuthExceptionTest {
    private static CustomAuthException r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(CustomAuthException.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        CustomAuthException tmp = manufacturedPojo(CustomAuthException.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

    @Test
    void test_Constructor() {
        ExceptionBody.ErrorDetails errorDetails = manufacturedPojo(ExceptionBody.ErrorDetails.class);
        assertThrows(CustomAuthException.class, () -> {
                    throw new CustomAuthException(errorDetails, "string");
                }
        );
    }
}