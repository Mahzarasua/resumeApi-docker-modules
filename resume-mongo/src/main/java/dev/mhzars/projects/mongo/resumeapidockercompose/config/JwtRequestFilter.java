package dev.mhzars.projects.mongo.resumeapidockercompose.config;

import org.springframework.stereotype.Component;

import dev.mhzars.projects.commons.resumeapidockercompose.config.CommonJwtRequestFilter;
import dev.mhzars.projects.mongo.resumeapidockercompose.service.MyUserDetailsService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtRequestFilter extends CommonJwtRequestFilter {

    public JwtRequestFilter(MyUserDetailsService jwtUserDetailsService, JwtTokenUtil jwtTokenUtil) {
        super(jwtUserDetailsService, jwtTokenUtil);
    }
}
