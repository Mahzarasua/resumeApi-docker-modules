package dev.mhzars.projects.mongo.resumeapidockercompose.validator;

import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.manufacturedCustomPojo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.mhzars.projects.commons.resumeapidockercompose.domain.auth.JwtRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomBadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JwtRequestValidatorTest {

    private JwtRequestValidator validator;

    @BeforeEach
    void init() {
        validator = new JwtRequestValidator();
    }

    @Test
    void validate() {
        JwtRequest request = manufacturedCustomPojo(JwtRequest.class);
        validator.validate(request);
    }

    @Test
    void validate_NoUser() {
        JwtRequest request = new JwtRequest(null, "null");
        assertThrows(CustomBadRequestException.class, () -> validator.validate(request));
    }

    @Test
    void validate_NoPass() {
        JwtRequest request = new JwtRequest("null", null);
        assertThrows(CustomBadRequestException.class, () -> validator.validate(request));
    }
}
