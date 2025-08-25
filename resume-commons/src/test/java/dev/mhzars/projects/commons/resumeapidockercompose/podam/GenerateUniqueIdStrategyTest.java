package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenerateUniqueIdStrategyTest {
    private static GenerateUniqueIdStrategy strategy;

    @BeforeEach
    void init() {
        strategy = new GenerateUniqueIdStrategy();
    }

    @Test
    void testStrategy() {
        String actual = strategy.getValue(Object.class, Collections.emptyList());
        assertNotNull(actual);
    }

    @Test
    void testStrategyPostgres() {
        System.setProperty("db.mongo", "false");
        String actual = strategy.getValue(Object.class, Collections.emptyList());
        assertNotNull(actual);
    }
}
