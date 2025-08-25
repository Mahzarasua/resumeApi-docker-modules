package dev.mhzars.projects.commons.resumeapidockercompose.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils;
import dev.mhzars.projects.commons.resumeapidockercompose.config.CommonCustomAuthenticationManager;
import dev.mhzars.projects.commons.resumeapidockercompose.config.CommonJwtTokenUtil;
import dev.mhzars.projects.commons.resumeapidockercompose.config.MyUserDetails;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.auth.JwtRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.auth.JwtResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.validator.JwtRequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

@Slf4j
class CommonJwtAuthenticationControllerTest {
    private static CommonJwtAuthenticationController controller;

    @BeforeEach
    void init() {
        CommonCustomAuthenticationManager authenticationManager =
                Mockito.mock(CommonCustomAuthenticationManager.class);
        CommonJwtTokenUtil jwtTokenUtil = Mockito.mock(CommonJwtTokenUtil.class);
        JwtRequestValidator validator = Mockito.mock(JwtRequestValidator.class);

        MyUserDetails userDetails = CommonTestUtils.manufacturedPojo(MyUserDetails.class);

        Mockito.doNothing().when(validator).validate(ArgumentMatchers.any());
        Mockito.doReturn(userDetails)
                .when(authenticationManager)
                .authentication(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        Mockito.doReturn("jwtToken").when(jwtTokenUtil).generateToken(ArgumentMatchers.any());

        controller =
                new CommonJwtAuthenticationController(
                        authenticationManager, jwtTokenUtil, validator);
    }

    @Test
    void givenValidRequest_whenCreateAuthenticationToken_thenSuccess() {
        JwtRequest request = CommonTestUtils.manufacturedPojo(JwtRequest.class);
        JwtResponse response = controller.createAuthenticationToken(request);
        log.info("Response: {}", response);
        assertNotNull(response);
    }
}
