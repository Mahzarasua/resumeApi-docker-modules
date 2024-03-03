package dev.mhzars.projects.mongo.resumeapidockercompose.model;

import dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;
    private String name;
    private int percentage;
    private String type;
    private LocalDateTime creationDate;

    public Skill(String name, int percentage, String type, LocalDateTime creationDate) {
        this.id = SpringUtils.generateUniqueObjectId();
        this.name = name;
        this.percentage = percentage;
        this.type = type;
        this.creationDate = creationDate;
    }
}
