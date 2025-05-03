package dev.mhzars.projects.mongo.resumeapidockercompose.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.mhzars.projects.commons.resumeapidockercompose.config.MyUserDetails;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomAuthException;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.ExceptionBody;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.AuthUser;
import dev.mhzars.projects.mongo.resumeapidockercompose.repository.AuthUserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    public MyUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user =
                authUserRepository
                        .findByUsername(username)
                        .orElseThrow(
                                () -> {
                                    log.warn("User {} not found", username);
                                    return new CustomAuthException(
                                            new ExceptionBody.ErrorDetails(
                                                    "credentials",
                                                    "You have entered an invalid username or password"),
                                            "Bad credentials");
                                });

        return new MyUserDetails(user.getCommonAuthUser());
    }
}
