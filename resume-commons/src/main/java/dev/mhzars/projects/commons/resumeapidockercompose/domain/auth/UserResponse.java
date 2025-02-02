package dev.mhzars.projects.commons.resumeapidockercompose.domain.auth;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private String id;
    private String username;
    private String password;
    private boolean active;
    private List<Role> roles;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Role {
        private String roleName;
    }
}
