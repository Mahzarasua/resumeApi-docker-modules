package dev.mhzars.projects.mongo.resumeapidockercompose.model;

import static dev.mhzars.projects.commons.resumeapidockercompose.utils.CommonSpringUtils.generateUniqueId;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonAuthRole;
import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonAuthUser;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Document
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser extends CommonAuthUser {
    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;

    public AuthUser(
            String username,
            String password,
            boolean active,
            LocalDateTime creationDate,
            List<CommonAuthRole> authRoles) {
        super(username, password, active, creationDate, authRoles);
        this.id = generateUniqueId();
    }

    public CommonAuthUser getCommonAuthUser() {
        List<CommonAuthRole> authRoleList = new ArrayList<>();
        if (this.getAuthRoles() != null) {
            for (CommonAuthRole authRole : this.getAuthRoles()) {
                authRoleList.add(
                        new CommonAuthRole(authRole.getRole(), authRole.getCreationDate()));
            }
        }
        return new CommonAuthUser(
                this.getUsername(),
                this.getPassword(),
                this.isActive(),
                this.getCreationDate(),
                authRoleList);
    }

    @Override
    public String toString() {
        return "AuthUser{" + "id=" + id + super.toString() + '}';
    }
}
