package dev.mhzars.projects.resume.resumeapidockercompose.controller;

import dev.mhzars.projects.resume.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.experience.ExperienceRequest;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.experience.ExperienceResponse;
import dev.mhzars.projects.resume.resumeapidockercompose.exception.ExceptionBody;
import dev.mhzars.projects.resume.resumeapidockercompose.service.ExperienceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static dev.mhzars.projects.resume.resumeapidockercompose.utils.SpringUtils.getUuid;

@Tag(name = "Experience")
@Validated
@RestController
@RequestMapping(value = "/api/v1/experience", produces = MediaType.APPLICATION_JSON_VALUE)
@SecurityRequirement(name = "jwtAuth")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized",
                content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema())}),
        @ApiResponse(responseCode = "400", description = "Bad Request",
                content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ExceptionBody.class))}),
        @ApiResponse(responseCode = "404", description = "Not Found",
                content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ExceptionBody.class))}),
        @ApiResponse(responseCode = "409", description = "Conflict",
                content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ExceptionBody.class))}),
        @ApiResponse(responseCode = "500", description = "Internal server error",
                content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ExceptionBody.class))})
})
public class ExperienceController {
    private final ExperienceService service;

    public ExperienceController(ExperienceService service) {
        this.service = service;
    }

    @GetMapping(value = "/{resumeId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This operation will return a list of records associated to the resume id")
    public ExperienceResponse getListbyResumeId(@PathVariable String resumeId) {
        return service.getListbyResumeId(getUuid(resumeId));
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This operation will generate a new record with the resume id provided " +
            "and will return the list of records associated to the resume id")
    public ExperienceResponse saveList(@RequestBody ExperienceRequest request) {
        return service.saveList(request);
    }

    @DeleteMapping(value = "/{resumeId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This operation will remove all records if only the resume id is provided or " +
            "it will remove the record matching the id and resume id provided " +
            "and will return the list of records associated to the resume id")
    public GenericDeleteResponse deleteRecords(@PathVariable String resumeId,
                                               @RequestParam(required = false) String id) {
        return (id != null) ? service.deleteRecordbyId(getUuid(resumeId), getUuid(id))
                : service.deleteRecordsbyResumeId(getUuid(resumeId));
    }
}
