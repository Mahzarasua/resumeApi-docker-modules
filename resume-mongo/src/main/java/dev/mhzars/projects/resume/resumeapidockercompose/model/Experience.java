package dev.mhzars.projects.resume.resumeapidockercompose.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static dev.mhzars.projects.resume.resumeapidockercompose.utils.SpringUtils.generateUniqueObjectId;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;
    private String title;
    private String company;
    private boolean currentJob;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime creationDate;

    public Experience(String title, String company, boolean currentJob, String description, LocalDate startDate, LocalDate endDate, LocalDateTime creationDate) {
        this.id = generateUniqueObjectId();
        this.title = title;
        this.company = company;
        this.currentJob = currentJob;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.creationDate = creationDate;
    }
}
