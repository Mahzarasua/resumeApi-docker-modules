package dev.mhzars.projects.mongo.resumeapidockercompose.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonResume;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 * Represents a professional resume document stored in MongoDB. Extends {@link CommonResume} to
 * inherit basic resume fields and adds MongoDB-specific document structure with nested collections
 * for education, experience, and skills.
 *
 * <p>This class is annotated for MongoDB document mapping and includes Lombok annotations for
 * automatic generation of getters, setters, constructors, and equals/hashCode methods.
 *
 * @see CommonResume Base class containing common resume fields
 * @see Education Education history entries
 * @see Experience Professional experience entries
 * @see Skill Technical/professional skills
 */
@EqualsAndHashCode(callSuper = true)
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resume extends CommonResume {
    /**
     * The unique identifier for the resume document in MongoDB. Uses MongoDB's native {@link
     * ObjectId} type with custom BSON mapping.
     *
     * <p>This field is mapped to the "_id" field in MongoDB documents.
     *
     * @see ObjectId MongoDB's 12-byte unique identifier
     */
    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;

    /**
     * List of education entries associated with this resume. Each entry represents a degree,
     * certification, or educational achievement.
     */
    private List<Education> educationList;

    /**
     * List of professional experience entries associated with this resume. Each entry represents a
     * job position or relevant work experience.
     */
    private List<Experience> experienceList;

    /**
     * List of skills associated with this resume. Includes both technical and professional skills.
     */
    private List<Skill> skillList;
}
