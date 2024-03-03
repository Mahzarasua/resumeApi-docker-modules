package dev.mhzars.projects.mongo.resumeapidockercompose.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomBadRequestException extends CustomErrorDetails {
    public CustomBadRequestException(List<ExceptionBody.ErrorDetails> errorDetails, String errorMessage) {
        super(errorDetails, errorMessage);
    }

    public CustomBadRequestException(String errorMessage) {
        super(errorMessage, "Bad Request");
    }
}
