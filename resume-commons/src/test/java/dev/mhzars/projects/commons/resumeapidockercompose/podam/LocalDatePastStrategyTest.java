package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocalDatePastStrategyTest {
    private static LocalDatePastStrategy strategy;

    @BeforeEach
    void init() {
        strategy = new LocalDatePastStrategy();
    }

    @Test
    void test() {
        LocalDate expectedDate = LocalDate.of(2023, 12, 25);
        LocalDate value = strategy.getValue(Object.class, Collections.emptyList());
        assertEquals(value, expectedDate);
    }
}
