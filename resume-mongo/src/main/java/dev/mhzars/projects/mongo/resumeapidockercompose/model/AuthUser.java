package dev.mhzars.projects.mongo.resumeapidockercompose.model;

import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.generateUniqueId;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonAuthRole;
import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonAuthUser;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Document
@Data
@NoArgsConstructor
public class AuthUser {
    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;

    private String username;
    private String password;
    private boolean active;
    private LocalDateTime creationDate;

    private List<AuthRole> authRoles;

    public AuthUser(
            String username,
            String password,
            boolean active,
            LocalDateTime creationDate,
            List<AuthRole> authRoles) {
        this.id = generateUniqueId();
        this.username = username;
        this.password = password;
        this.active = active;
        this.creationDate = creationDate;
        this.authRoles = authRoles;
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
