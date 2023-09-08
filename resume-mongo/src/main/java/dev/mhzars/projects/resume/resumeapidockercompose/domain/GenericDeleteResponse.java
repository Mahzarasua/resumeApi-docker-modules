package dev.mhzars.projects.resume.resumeapidockercompose.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "DeleteResponse")
public class GenericDeleteResponse {
    private String id;
    private String resumeId;

    public GenericDeleteResponse(String resumeId) {
        this.resumeId = resumeId;
    }

    public GenericDeleteResponse(String id, String resumeId) {
        this.id = id;
        this.resumeId = resumeId;
    }
}
