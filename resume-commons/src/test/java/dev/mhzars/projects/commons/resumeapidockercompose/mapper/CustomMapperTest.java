package dev.mhzars.projects.commons.resumeapidockercompose.mapper;

import static dev.mhzars.projects.commons.resumeapidockercompose.mapper.CommonCustomMapper.COMMON_MAPPER;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class CustomMapperTest {

    @Test
    void testUUIDtoString() {
        UUID newId = COMMON_MAPPER.convertValue(UUID.randomUUID().toString(), UUID.class);
        log.info("UUID {}", newId);
        assertNotNull(newId);
    }

    @Test
    void testStringtoUUID() {
        String newId = COMMON_MAPPER.convertValue(UUID.randomUUID(), String.class);
        log.info("String {}", newId);
        assertNotNull(newId);
    }
}
