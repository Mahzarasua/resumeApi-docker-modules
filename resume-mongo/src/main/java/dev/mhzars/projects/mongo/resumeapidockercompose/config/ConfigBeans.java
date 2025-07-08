package dev.mhzars.projects.mongo.resumeapidockercompose.config;

import static dev.mhzars.projects.commons.resumeapidockercompose.config.WebConfigWhiteList.getAuthWhitelist;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import dev.mhzars.projects.commons.resumeapidockercompose.config.CommonConfigBeans;
import dev.mhzars.projects.commons.resumeapidockercompose.config.CommonCustomAuthenticationManager;
import dev.mhzars.projects.commons.resumeapidockercompose.config.CommonJwtAuthenticationEntryPoint;
import dev.mhzars.projects.commons.resumeapidockercompose.config.CommonJwtRequestFilter;
import dev.mhzars.projects.commons.resumeapidockercompose.config.CommonJwtTokenUtil;
import dev.mhzars.projects.commons.resumeapidockercompose.controller.CommonResumeController;
import dev.mhzars.projects.commons.resumeapidockercompose.validator.JwtRequestValidator;
import dev.mhzars.projects.commons.resumeapidockercompose.validator.ResumeValidator;
import dev.mhzars.projects.mongo.resumeapidockercompose.service.MyUserDetailsService;
import dev.mhzars.projects.mongo.resumeapidockercompose.service.ResumeServiceImpl;

@Configuration
public class ConfigBeans extends CommonConfigBeans {

    @Bean
    public CommonJwtAuthenticationEntryPoint getCommonJwtAuthenticationEntryPoint() {
        return new CommonJwtAuthenticationEntryPoint();
    }

    @Bean
    public CommonCustomAuthenticationManager getCustomAuthenticationManager(
            MyUserDetailsService userDetailsService, PasswordEncoder encoder) {
        return new CommonCustomAuthenticationManager(userDetailsService, encoder);
    }

    @Bean
    public CommonJwtRequestFilter getCommonJwtRequestFilter(
            MyUserDetailsService jwtUserDetailsService, CommonJwtTokenUtil jwtTokenUtil) {
        return new CommonJwtRequestFilter(jwtUserDetailsService, jwtTokenUtil);
    }

    @Bean
    public JwtRequestValidator getJwtRequestValidator() {
        return new JwtRequestValidator();
    }

    @Bean
    public ResumeValidator getResumeValidator() {
        return new ResumeValidator();
    }

    @Bean
    @Primary
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            CommonCustomAuthenticationManager customAuthenticationManager,
            CommonJwtRequestFilter jwtRequestFilter,
            CommonJwtAuthenticationEntryPoint jwtAuthenticationEntryPoint)
            throws Exception {
        return http.authorizeHttpRequests(
                        authz ->
                                authz.requestMatchers(getAuthWhitelist())
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated())
                .httpBasic(withDefaults())
                .authenticationManager(customAuthenticationManager)
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CommonResumeController getCommonResumeController(ResumeServiceImpl service) {
        return new CommonResumeController(service);
    }
}
