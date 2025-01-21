package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmailStrategyTest {
    private static EmailStrategy strategy;

    @BeforeEach
    void init() {
        strategy = new EmailStrategy();
    }

    @Test
    void testEmailStrategyGetValue() {
        // Define the expected value
        String expected = "aaa.bbb@ccc.ddd";
        String actual = strategy.getValue(Object.class, Collections.emptyList());
        // Assert that the actual value matches the expected value
        assertEquals(expected, actual);
    }
}
