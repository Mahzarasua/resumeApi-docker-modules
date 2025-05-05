package dev.mhzars.projects.mongo.resumeapidockercompose.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonEducation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 * Represents an education entry within a resume, stored as a subdocument in MongoDB. Extends {@link
 * CommonEducation} to inherit basic education fields and adds MongoDB-specific document
 * identifiers.
 *
 * <p>This class is used as a nested document within the {@link Resume} class and contains
 * information about academic degrees, certifications, or other educational achievements.
 *
 * <p>The class includes Lombok annotations for automatic generation of boilerplate code and MongoDB
 * annotations for proper document mapping.
 *
 * @see Resume The parent resume document containing education entries
 * @see CommonEducation Base class containing common education fields
 */
@EqualsAndHashCode(callSuper = true)
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education extends CommonEducation {
    /**
     * The unique identifier for the education entry in MongoDB. Uses MongoDB's native {@link
     * ObjectId} type with custom BSON mapping.
     *
     * <p>This field is mapped to the "_id" field in MongoDB documents but is typically managed
     * automatically by MongoDB when embedded within a {@link Resume} document.
     *
     * @see ObjectId MongoDB's 12-byte unique identifier
     * @see BsonId Annotation indicating this is the primary identifier
     * @see BsonProperty Custom mapping to MongoDB's "_id" field
     */
    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;
}
