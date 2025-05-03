package dev.mhzars.projects.postgres.resumeapidockercompose.utils;

import static dev.mhzars.projects.commons.resumeapidockercompose.utils.CommonSpringUtils.generateUniqueId;
import static dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils.validateObjectId;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomBadRequestException;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class SpringUtilsTest {
    @Test
    void test() {
        UUID id = generateUniqueId();
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
