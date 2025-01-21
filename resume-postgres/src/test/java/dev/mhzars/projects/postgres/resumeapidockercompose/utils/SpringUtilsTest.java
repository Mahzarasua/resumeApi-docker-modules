package dev.mhzars.projects.postgres.resumeapidockercompose.utils;

import static dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils.generateUniqueObjectId;
import static dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils.validateObjectId;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomBadRequestException;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class SpringUtilsTest {

    @Test
    void test() {
        UUID id = generateUniqueObjectId();
        assertNotNull(id);
        assertNotNull(validateObjectId(id.toString()));
    }

    @Test
    void testException() {
        assertThrows(CustomBadRequestException.class, () -> validateObjectId(null));
        assertThrows(CustomBadRequestException.class, () -> validateObjectId(""));
        assertThrows(CustomBadRequestException.class, () -> validateObjectId("aaa"));
    }
}
