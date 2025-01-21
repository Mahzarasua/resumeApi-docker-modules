package dev.mhzars.projects.mongo.resumeapidockercompose.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.mhzars.projects.mongo.resumeapidockercompose.model.AuthUser;
import java.util.Optional;

@Repository
public interface AuthUserRepository extends MongoRepository<AuthUser, String> {
    Optional<AuthUser> findByUsername(String username);
}
