package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PhoneStrategyTest {

    private static PhoneStrategy phoneStrategy;

    @BeforeEach
    void init() {
        phoneStrategy = new PhoneStrategy();
    }

    @Test
    void testPhoneStrategyGetValue() {
        // Define the expected value
        String expected = "+521234567891";
        String actual = phoneStrategy.getValue(Object.class, Collections.emptyList());
        // Assert that the actual value matches the expected value
        assertEquals(expected, actual);
    }
}
