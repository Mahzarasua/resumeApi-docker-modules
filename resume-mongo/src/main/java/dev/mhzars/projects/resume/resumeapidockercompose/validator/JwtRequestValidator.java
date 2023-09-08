package dev.mhzars.projects.resume.resumeapidockercompose.validator;

import dev.mhzars.projects.resume.resumeapidockercompose.domain.auth.JwtRequest;
import dev.mhzars.projects.resume.resumeapidockercompose.exception.CustomBadRequestException;
import dev.mhzars.projects.resume.resumeapidockercompose.exception.ExceptionBody;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static dev.mhzars.projects.resume.resumeapidockercompose.validator.CustomValidationUtils.CUSTOM_ERROR_MSG;
import static dev.mhzars.projects.resume.resumeapidockercompose.validator.CustomValidationUtils.validateRequiredString;

@Service
public class JwtRequestValidator implements CustomValidator<JwtRequest> {
    @Override
    public void validate(JwtRequest request) {
        List<ExceptionBody.ErrorDetails> errorDetails = new ArrayList<>();

        validateRequiredString(errorDetails, request.getUsername(), "username");
        validateRequiredString(errorDetails, request.getPassword(), "password");

        if (!errorDetails.isEmpty()) throw new CustomBadRequestException(errorDetails, CUSTOM_ERROR_MSG);
    }
}
