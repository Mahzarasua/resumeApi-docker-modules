package dev.mhzars.projects.mongo.resumeapidockercompose.model;

import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.generateUniqueId;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonAuthRole;
import java.time.LocalDateTime;
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
public class AuthRole extends CommonAuthRole {
    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;

    public AuthRole(String role, LocalDateTime creationDate) {
        super(role, creationDate);
        this.id = generateUniqueId();
    }

    @Override
    public String toString() {
        return "AuthRole{" + "id=" + id + super.toString() + '}';
    }
}
