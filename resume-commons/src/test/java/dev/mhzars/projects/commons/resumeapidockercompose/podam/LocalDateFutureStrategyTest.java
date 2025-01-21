package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocalDateFutureStrategyTest {
    private static LocalDateFutureStrategy strategy;

    @BeforeEach
    void init() {
        strategy = new LocalDateFutureStrategy();
    }

    @Test
    void test() {
        LocalDate value = strategy.getValue(Object.class, Collections.emptyList());
        assertNotNull(value);
        assertTrue(LocalDate.now().isAfter(value) || LocalDate.now().isEqual(value));
    }
}
