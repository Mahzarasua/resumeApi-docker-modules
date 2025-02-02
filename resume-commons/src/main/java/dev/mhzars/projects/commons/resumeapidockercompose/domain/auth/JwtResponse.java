package dev.mhzars.projects.commons.resumeapidockercompose.domain.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema
public class JwtResponse implements Serializable {
    @Serial private static final long serialVersionUID = -5172680586217734873L;

    private String jwtToken;
}
