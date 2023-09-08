package dev.mhzars.projects.resume.resumeapidockercompose.initializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import dev.mhzars.projects.resume.resumeapidockercompose.model.AuthRole;
import dev.mhzars.projects.resume.resumeapidockercompose.model.AuthUser;
import dev.mhzars.projects.resume.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.resume.resumeapidockercompose.repository.AuthUserRepository;
import dev.mhzars.projects.resume.resumeapidockercompose.repository.ResumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static dev.mhzars.projects.resume.resumeapidockercompose.utils.SpringUtils.OBJECT_MAPPER;
import static dev.mhzars.projects.resume.resumeapidockercompose.utils.SpringUtils.mapFromJsonList;

@Component
@Slf4j
public class InitializeDB implements CommandLineRunner {

    public static final LocalDateTime CREATION_DATE = LocalDateTime.now();
    public static final String USER_DISABLED = "disabled";
    private static final LocalDateTime creationDate = CREATION_DATE;
    private final AuthUserRepository userRepo;
    private final ResumeRepository resumeRepo;

    public InitializeDB(AuthUserRepository userRepo, ResumeRepository resumeRepo) {
        this.userRepo = userRepo;
        this.resumeRepo = resumeRepo;
    }

    @Override
    public void run(String... args) {
        try {
            readOrCreateDBUser();
            readOrCreateSampleResume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readOrCreateSampleResume() {
        String json;
        List<Resume> resumes;
        TypeReference<List<Resume>> clazz = new TypeReference<>() {
        };
        try (InputStream in = InitializeDB.class.getClassLoader().getResourceAsStream("resumeSample.json")) {
            JsonNode jsonNode = OBJECT_MAPPER.readValue(in, JsonNode.class);
            json = OBJECT_MAPPER.writeValueAsString(jsonNode);
            resumes = jsonNode.isArray() ?
                    mapFromJsonList(json, clazz) :
                    Collections.singletonList(OBJECT_MAPPER.readValue(json, Resume.class));
            for (Resume resume : resumes) {
                log.info("Resume loaded: {}", resume);

                if (resumeRepo.findFirstByFirstName(resume.getFirstName()).isPresent()) {
                    log.info("Resume found: {}", resume);
                } else {
                    resume.setId(null);
                    resume.getEducationList().forEach(e -> e.setId(null));
                    resume.getSkillList().forEach(e -> e.setId(null));
                    resume.getExperienceList().forEach(e -> e.setId(null));
                    resumeRepo.save(resume);
                }
            }
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
        }

    }

    private void readOrCreateDBUser() throws Exception {
        String json;
        TypeReference<List<AuthUser>> clazz = new TypeReference<>() {
        };
        List<AuthUser> dbUsers;
        try (InputStream in = InitializeDB.class.getClassLoader().getResourceAsStream("dbUser.json")) {
            JsonNode jsonNode = OBJECT_MAPPER.readValue(in, JsonNode.class);
            json = OBJECT_MAPPER.writeValueAsString(jsonNode);
            dbUsers = jsonNode.isArray() ?
                    mapFromJsonList(json, clazz) :
                    Collections.singletonList(OBJECT_MAPPER.readValue(json, AuthUser.class));
            log.info("DbUser loaded: {}", dbUsers);
            for (AuthUser dbUser : dbUsers) {
                Optional<AuthUser> authUser = userRepo.findByUsername(dbUser.getUsername());
                if (authUser.isPresent()) {
                    log.info("User found for: {}", OBJECT_MAPPER.writeValueAsString(authUser.get()));
                } else {
                    List<AuthRole> authRoleList = new ArrayList<>();
                    for (AuthRole r :
                            dbUser.getAuthRoles()) {
                        authRoleList.add(new AuthRole(r.getRole(), creationDate));
                    }

                    AuthUser user = new AuthUser(dbUser.getUsername(), dbUser.getPassword(), dbUser.isActive(), creationDate
                            , authRoleList);
                    userRepo.save(user);
                }
            }
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
        }
    }

}
