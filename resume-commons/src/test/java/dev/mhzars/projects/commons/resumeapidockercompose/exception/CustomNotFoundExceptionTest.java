package dev.mhzars.projects.commons.resumeapidockercompose.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CustomNotFoundExceptionTest {

    @Test
    void test_Constructor() {
        assertThrows(
                CustomNotFoundException.class,
                () -> {
                    throw new CustomNotFoundException("string");
                });
    }
}
