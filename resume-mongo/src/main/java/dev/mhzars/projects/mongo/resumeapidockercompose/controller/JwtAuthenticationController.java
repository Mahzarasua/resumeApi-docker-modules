package dev.mhzars.projects.mongo.resumeapidockercompose.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.mhzars.projects.commons.resumeapidockercompose.config.CommonCustomAuthenticationManager;
import dev.mhzars.projects.commons.resumeapidockercompose.config.CommonJwtTokenUtil;
import dev.mhzars.projects.commons.resumeapidockercompose.controller.CommonJwtAuthenticationController;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.auth.JwtRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.auth.JwtResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.ExceptionBody;
import dev.mhzars.projects.commons.resumeapidockercompose.validator.JwtRequestValidator;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Authentication")
@CrossOrigin
@Validated
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(
        value = {
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = {
                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema())
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = {
                        @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = ExceptionBody.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = {
                        @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = ExceptionBody.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = {
                        @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = ExceptionBody.class))
                    })
        })
public class JwtAuthenticationController extends CommonJwtAuthenticationController {

    public JwtAuthenticationController(
            CommonCustomAuthenticationManager authenticationManager,
            CommonJwtTokenUtil jwtTokenUtil,
            JwtRequestValidator validator) {
        super(authenticationManager, jwtTokenUtil, validator);
    }

    @PostMapping(value = "/authenticate")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest authRequest) {
        return super.createAuthenticationToken(authRequest);
    }
}
