package dev.mhzars.projects.mongo.resumeapidockercompose.model;

import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.generateUniqueId;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 * Represents an authentication role document stored in MongoDB. Used for managing user
 * authorization roles and their creation timestamps.
 *
 * <p>Each role instance contains a unique identifier, role name, and creation date, and is
 * persisted as a separate document in MongoDB.
 *
 * <p>The class uses Lombok annotations for boilerplate code reduction and MongoDB annotations for
 * proper document mapping.
 */
@Document
@Data
@NoArgsConstructor
public class AuthRole {
    /**
     * The unique identifier for the role document in MongoDB. Uses MongoDB's native {@link
     * ObjectId} type with proper BSON mapping.
     *
     * <p>This field is mapped to the "_id" field in MongoDB documents and is automatically
     * generated when creating new instances through the constructor.
     *
     * @see ObjectId MongoDB's 12-byte unique identifier
     */
    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;

    /** The name/type of the authorization role (e.g., "ADMIN", "USER"). */
    private String role;

    /** The date and time when the role was created in the system. */
    private LocalDateTime creationDate;

    /**
     * Constructs a new AuthRole with the specified role name and creation date. Automatically
     * generates a unique ID for the document.
     *
     * @param role The name of the role
     * @param creationDate The date/time when the role was created
     */
    public AuthRole(String role, LocalDateTime creationDate) {
        this.id = generateUniqueId();
        this.role = role;
        this.creationDate = creationDate;
    }
}
