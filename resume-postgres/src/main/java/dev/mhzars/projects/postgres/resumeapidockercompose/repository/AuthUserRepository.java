package dev.mhzars.projects.postgres.resumeapidockercompose.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.mhzars.projects.postgres.resumeapidockercompose.model.AuthUser;
import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, String> {
    Optional<AuthUser> findByUsername(String username);
}
