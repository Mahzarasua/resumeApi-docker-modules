package dev.mhzars.projects.mongo.resumeapidockercompose.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.mhzars.projects.commons.resumeapidockercompose.config.MyUserDetails;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomAuthException;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.ExceptionBody;
import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonAuthUser;
import dev.mhzars.projects.mongo.resumeapidockercompose.mapper.CustomMapper;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.AuthUser;
import dev.mhzars.projects.mongo.resumeapidockercompose.repository.AuthUserRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    private final CustomMapper mapper;

    public MyUserDetailsService(AuthUserRepository authUserRepository, CustomMapper mapper) {
        this.authUserRepository = authUserRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> user = authUserRepository.findByUsername(username);
        if (user.isEmpty()) {
            log.warn("User {} not found", username);
            throw new CustomAuthException(
                    new ExceptionBody.ErrorDetails(
                            "credentials", "You have entered an invalid username or password"),
                    "Bad credentials");
        }

        CommonAuthUser commonAuthUser = mapper.map(user.get(), CommonAuthUser.class);

        return mapper.map(commonAuthUser, MyUserDetails.class);
    }
}
