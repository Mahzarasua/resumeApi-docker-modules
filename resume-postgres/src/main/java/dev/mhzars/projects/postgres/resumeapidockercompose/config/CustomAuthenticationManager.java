package dev.mhzars.projects.postgres.resumeapidockercompose.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.mhzars.projects.commons.resumeapidockercompose.config.CommonCustomAuthenticationManager;
import dev.mhzars.projects.postgres.resumeapidockercompose.service.MyUserDetailsService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAuthenticationManager extends CommonCustomAuthenticationManager {

    public CustomAuthenticationManager(
            MyUserDetailsService userDetailsService, PasswordEncoder encoder) {
        super(userDetailsService, encoder);
    }
}
