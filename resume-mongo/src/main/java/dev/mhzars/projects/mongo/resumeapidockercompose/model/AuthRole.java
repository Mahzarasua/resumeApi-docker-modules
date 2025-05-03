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

@Document
@Data
@NoArgsConstructor
public class AuthRole {
    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;

    private String role;
    private LocalDateTime creationDate;

    public AuthRole(String role, LocalDateTime creationDate) {
        this.id = generateUniqueId();
        this.role = role;
        this.creationDate = creationDate;
    }
}
