package dev.mhzars.projects.mongo.resumeapidockercompose.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonExperience;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience extends CommonExperience {
    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;

    @Override
    public String toString() {
        return "{"
                + "id="
                + id
                + ", title="
                + getTitle()
                + ", company="
                + getCompany()
                + ", currentJob="
                + isCurrentJob()
                + ", description="
                + getDescription()
                + ", startDate="
                + getStartDate()
                + ", endDate="
                + getEndDate()
                + ", creationDate="
                + getCreationDate()
                + "}";
    }
}
