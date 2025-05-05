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

/**
 * Represents a professional work experience entry within a resume, stored as a subdocument in
 * MongoDB. Extends {@link CommonExperience} to inherit common experience fields and adds
 * MongoDB-specific identifiers.
 *
 * <p>This class is used as a nested document within the {@link Resume} class to store information
 * about professional positions, internships, or other relevant work history.
 *
 * <p>The class includes Lombok annotations for automatic generation of boilerplate code and MongoDB
 * annotations for proper document mapping and serialization.
 *
 * @see Resume The parent resume document containing experience entries
 * @see CommonExperience Base class containing common experience fields
 * @see ObjectId MongoDB's unique identifier implementation
 */
@EqualsAndHashCode(callSuper = true)
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience extends CommonExperience {
    /**
     * The unique identifier for the experience entry in MongoDB. Uses MongoDB's native {@link
     * ObjectId} type with proper BSON mapping.
     *
     * <p>This field is mapped to MongoDB's standard "_id" field but is typically auto-generated
     * when the document is first saved to the database.
     *
     * <p>Note: For embedded documents within a {@link Resume}, this ID may be managed by the parent
     * document's persistence lifecycle.
     *
     * @see ObjectId MongoDB's 12-byte unique identifier format
     * @see BsonId Marker annotation for the primary identifier field
     * @see BsonProperty Specifies the exact field name ("_id") in MongoDB
     */
    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;
}
