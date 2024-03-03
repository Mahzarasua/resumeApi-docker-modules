package dev.mhzars.projects.mongo.resumeapidockercompose.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;

    private String firstName;
    private String lastName;
    private String title;
    private String city;
    private String state;
    private String country;
    private String email;
    private String phone;
    private String summary;
    private LocalDateTime creationDate;

    private List<Education> educationList;
    private List<Experience> experienceList;
    private List<Skill> skillList;
}
