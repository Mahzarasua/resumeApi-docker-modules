package dev.mhzars.projects.mongo.resumeapidockercompose.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.mhzars.projects.mongo.resumeapidockercompose.model.Resume;
import java.util.Optional;
import org.bson.types.ObjectId;

@Repository
public interface ResumeRepository extends MongoRepository<Resume, ObjectId> {
    Optional<Resume> findFirstByFirstName(String firstName);
}
