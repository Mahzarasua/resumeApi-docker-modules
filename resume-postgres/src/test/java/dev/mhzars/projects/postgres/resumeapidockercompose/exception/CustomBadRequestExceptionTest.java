package dev.mhzars.projects.postgres.resumeapidockercompose.exception;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedPojo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomBadRequestExceptionTest {

    @Test
    void test_Constructor() {
        ExceptionBody.ErrorDetails errorDetails = manufacturedPojo(ExceptionBody.ErrorDetails.class);
        List<ExceptionBody.ErrorDetails> detailsList = Collections.singletonList(errorDetails);
        assertThrows(CustomBadRequestException.class, () -> {
                    throw new CustomBadRequestException(detailsList, "string");
                }
        );
    }

}