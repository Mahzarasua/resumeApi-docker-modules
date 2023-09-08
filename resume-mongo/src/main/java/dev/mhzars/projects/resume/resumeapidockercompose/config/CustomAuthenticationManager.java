package dev.mhzars.projects.resume.resumeapidockercompose.config;

import dev.mhzars.projects.resume.resumeapidockercompose.exception.CustomAuthException;
import dev.mhzars.projects.resume.resumeapidockercompose.exception.ExceptionBody;
import dev.mhzars.projects.resume.resumeapidockercompose.service.MyUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomAuthenticationManager implements AuthenticationManager {
    public static final String USER_PROVIDED_IS_DISABLED = "The user provided is disabled";
    public static final String INVALID_USERNAME_OR_PASSWORD = "You have entered an invalid username or password";
    private static final String CREDENTIALS_EXCEPTION_FIELDNAME = "credentials";
    private static final String CUSTOM_AUTH_EXCEPTION_BAD_CREDENTIALS = "Bad credentials";
    private final MyUserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

    public CustomAuthenticationManager(MyUserDetailsService userDetailsService, PasswordEncoder encoder) {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    private static void credentialsException(String errorMessage) {
        throw new CustomAuthException(
                new ExceptionBody.ErrorDetails(CREDENTIALS_EXCEPTION_FIELDNAME, errorMessage)
                , CUSTOM_AUTH_EXCEPTION_BAD_CREDENTIALS);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = String.valueOf(authentication.getPrincipal());
        String password = String.valueOf(authentication.getCredentials());

        commonValidations(username, password);
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    public UserDetails authentication(String username, String password) throws AuthenticationException {
        return commonValidations(username, password);
    }

    private UserDetails commonValidations(String username, String password) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!encoder.matches(password, userDetails.getPassword())) {
            log.debug("You have entered an invalid username {} or password {}", username, password);
            credentialsException(INVALID_USERNAME_OR_PASSWORD);
        }
        if (!userDetails.isEnabled()) {
            log.debug("The user provided is disabled {}", username);
            credentialsException(USER_PROVIDED_IS_DISABLED);
        }
        return userDetails;
    }
}
