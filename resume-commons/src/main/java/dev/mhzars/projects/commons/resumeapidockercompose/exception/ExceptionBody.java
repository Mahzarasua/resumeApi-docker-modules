package dev.mhzars.projects.commons.resumeapidockercompose.exception;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionBody {
    private LocalDateTime timestamp;
    private Integer statusCode;
    private String error;
    private String message;
    private String path;
    private List<ErrorDetails> details;

    public static ExceptionBody.ErrorDetails newErrorDetail(String errorMessage, String fieldName) {
        ExceptionBody.ErrorDetails error = new ExceptionBody.ErrorDetails();
        error.setErrorMessage(errorMessage);
        error.setFieldName(fieldName);
        return error;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorDetails implements Serializable {
        @Serial private static final long serialVersionUID = 1067005304158628377L;

        private String fieldName;
        private String errorMessage;
    }
}
