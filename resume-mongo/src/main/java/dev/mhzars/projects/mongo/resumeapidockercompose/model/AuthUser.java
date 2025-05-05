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

/**
 * Represents an authentication user entity stored in MongoDB. Contains user credentials, status,
 * and associated roles for system authorization.
 */
@Document
@Data
@NoArgsConstructor
public class AuthUser {
    /**
     * The unique identifier for the user document in MongoDB. Uses MongoDB's native {@link
     * ObjectId} type with proper BSON mapping.
     *
     * <p>This field is mapped to the "_id" field in MongoDB documents.
     */
    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;

    /** The username for authentication (typically unique across the system). */
    private String username;

    /** The encrypted/hashed password for user authentication. */
    private String password;

    /** Flag indicating whether the user account is active/enabled. */
    private boolean active;

    /** The date and time when the user account was created. */
    private LocalDateTime creationDate;

    /** List of roles assigned to the user for authorization purposes. */
    private List<AuthRole> authRoles;

    /**
     * Constructs a new AuthUser with the specified properties. Automatically generates a unique ID
     * for the document.
     *
     * @param username The user's login name
     * @param password The user's encrypted password
     * @param active Whether the user account is active
     * @param creationDate When the user account was created
     * @param authRoles List of roles assigned to the user
     */
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

    /**
     * Converts this MongoDB AuthUser entity to a common DTO representation.
     *
     * @return CommonAuthUser DTO containing the user's information
     */
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
