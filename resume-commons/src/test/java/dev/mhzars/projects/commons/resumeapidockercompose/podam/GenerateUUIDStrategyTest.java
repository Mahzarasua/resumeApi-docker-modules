package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenerateUUIDStrategyTest {
    private static GenerateUUIDStrategy strategy;

    @BeforeEach
    void init() {
        strategy = new GenerateUUIDStrategy();
    }

    @Test
    void testStrategy() {
        String actual = strategy.getValue(Object.class, Collections.emptyList());
        assertNotNull(actual);
    }
}
