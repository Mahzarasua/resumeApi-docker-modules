package dev.mhzars.projects.resume.resumeapidockercompose.repository;

import dev.mhzars.projects.resume.resumeapidockercompose.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, String> {
    Optional<AuthUser> findByUsername(String username);
}
