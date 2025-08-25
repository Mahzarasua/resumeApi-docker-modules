package dev.mhzars.projects.commons.resumeapidockercompose.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommonJwtAuthenticationEntryPointTest {

    private CommonJwtAuthenticationEntryPoint authenticationEntryPoint;

    @BeforeEach
    void init() {
        authenticationEntryPoint = new CommonJwtAuthenticationEntryPoint();
    }

    @Test
    void commence() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException ex = new AuthenticationCredentialsNotFoundException("");

        authenticationEntryPoint.commence(request, response, ex);

        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
    }
}
