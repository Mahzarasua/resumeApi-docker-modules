package dev.mhzars.projects.mongo.resumeapidockercompose.service;

import static dev.mhzars.projects.commons.resumeapidockercompose.utils.CommonSpringUtils.mapFromJsonList;
import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.generateUniqueId;
import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.removeFromList;
import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.validateObjectId;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceDomain;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.commons.resumeapidockercompose.service.ExperienceService;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Experience;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.mongo.resumeapidockercompose.repository.ResumeRepository;
import dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringResumeRepo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    public static final String EXCEPTION_MSG = "No Experience record was found for resumeId %s";
    private final ResumeRepository repo;
    private final SpringResumeRepo checkResume;

    @Override
    public ExperienceResponse getListbyResumeId(String resumeId) throws JsonProcessingException {
        Resume resume = checkResume.checkResumeId(resumeId);
        List<Experience> list = resume.getExperienceList();

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(EXCEPTION_MSG, resumeId));

        return new ExperienceResponse(mapFromJsonList(list, ExperienceDomain.class));
    }

    @Override
    public ExperienceResponse saveList(ExperienceRequest request) throws JsonProcessingException {
        String resumeId = request.getExperienceList().get(0).getResumeId();
        Resume resume = checkResume.checkResumeId(resumeId);

        request.getExperienceList().forEach(e -> e.setResumeId(String.valueOf(resume.getId())));
        List<Experience> experienceList =
                mapFromJsonList(request.getExperienceList(), Experience.class);
        for (Experience e : experienceList) {
            if (e.getId() == null) e.setId(generateUniqueId());
            if (e.getCreationDate() == null) e.setCreationDate(LocalDateTime.now());
            if (!resume.getExperienceList().contains(e)) {
                resume.getExperienceList().add(e);
            }
        }

        repo.save(resume);

        return getListbyResumeId(resumeId);
    }

    @Override
    public GenericDeleteResponse deleteRecordsbyResumeId(String resumeId) {
        Resume resume = checkResume.checkResumeId(resumeId);

        List<Experience> list = resume.getExperienceList();

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(EXCEPTION_MSG, resumeId));

        resume.getExperienceList().clear();
        repo.save(resume);

        return new GenericDeleteResponse(resumeId);
    }

    @Override
    public GenericDeleteResponse deleteRecordbyId(String resumeId, String id) {
        Resume resume = checkResume.checkResumeId(resumeId);
        checkResumeExperienceList(resume, id);
        removeFromList(
                resume.getExperienceList(),
                row ->
                        Objects.equals(row.getId(), validateObjectId(id))
                                && Objects.equals(resume.getId(), validateObjectId(resumeId)));
        repo.save(resume);
        return new GenericDeleteResponse(id, resumeId);
    }

    private void checkResumeExperienceList(Resume resume, String id) {
        boolean recNotFound = true;
        for (Experience e : resume.getExperienceList()) {
            if (Objects.equals(e.getId(), validateObjectId(id))) {
                recNotFound = false;
            }
        }
        if (recNotFound)
            throw new CustomNotFoundException(
                    String.format("Experience with id %s was not found", id));
    }
}
