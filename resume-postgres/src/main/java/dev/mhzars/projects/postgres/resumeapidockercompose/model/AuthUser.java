package dev.mhzars.projects.postgres.resumeapidockercompose.model;

import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonAuthRole;
import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonAuthUser;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class AuthUser {
    @Id @GeneratedValue private UUID id;
    private String username;
    private String password;
    private boolean active;
    private LocalDateTime creationDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<AuthRole> authRoles;

    public AuthUser(
            String username,
            String password,
            boolean active,
            LocalDateTime creationDate,
            List<AuthRole> authRoles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.creationDate = creationDate;
        this.authRoles = authRoles;
    }

    @PrePersist
    @PreUpdate
    public void prepareRole() {
        authRoles.forEach(r -> r.setUser(this));
    }

    public CommonAuthUser getCommonAuthUser() {
        List<CommonAuthRole> authRoleList = new ArrayList<>();
        if (this.getAuthRoles() != null) {
            for (AuthRole authRole : this.getAuthRoles()) {
                CommonAuthRole commonAuthRole =
                        CommonAuthRole.builder()
                                .role(authRole.getRole())
                                .creationDate(authRole.getCreationDate())
                                .build();
                authRoleList.add(commonAuthRole);
            }
        }
        return CommonAuthUser.builder()
                .username(this.username)
                .password(this.password)
                .active(this.active)
                .authRoles(authRoleList)
                .creationDate(this.creationDate)
                .build();
    }
}
