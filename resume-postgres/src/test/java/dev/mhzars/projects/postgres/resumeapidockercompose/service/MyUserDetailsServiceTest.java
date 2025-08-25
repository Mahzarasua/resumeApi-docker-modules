package dev.mhzars.projects.postgres.resumeapidockercompose.service;

import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.RESUME_ID;
import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedPojo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.security.core.userdetails.UserDetails;

import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomAuthException;
import dev.mhzars.projects.postgres.resumeapidockercompose.model.AuthUser;
import dev.mhzars.projects.postgres.resumeapidockercompose.repository.AuthUserRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

@Slf4j
class MyUserDetailsServiceTest {

    private static MyUserDetailsService service;

    @BeforeEach
    void init() {
        AuthUserRepository authUserRepository = Mockito.mock(AuthUserRepository.class);

        AuthUser user = manufacturedPojo(AuthUser.class);
        Optional<AuthUser> userOptional = Optional.ofNullable(user);
        Optional<AuthUser> userEmpty = Optional.empty();

        Mockito.doReturn(userOptional)
                .when(authUserRepository)
                .findByUsername(ArgumentMatchers.anyString());
        Mockito.doReturn(userEmpty).when(authUserRepository).findByUsername(RESUME_ID);

        service = new MyUserDetailsService(authUserRepository);
    }

    @Test
    void loadUserByUsername() {
        UserDetails response = service.loadUserByUsername("Test");
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void loadUserByUsername_Negative() {
        assertThrows(CustomAuthException.class, () -> service.loadUserByUsername(RESUME_ID));
    }
}
