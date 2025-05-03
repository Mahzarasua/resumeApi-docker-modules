package dev.mhzars.projects.mongo.resumeapidockercompose.service;

import static dev.mhzars.projects.commons.resumeapidockercompose.mapper.CommonCustomMapper.COMMON_MAPPER;
import static dev.mhzars.projects.commons.resumeapidockercompose.utils.CommonSpringUtils.mapFromJsonList;
import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.generateUniqueId;
import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.validateObjectId;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.ResumeIdResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.ResumeResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.commons.resumeapidockercompose.validator.CommonResumeValidator;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.resume.ResumeRequest;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.mongo.resumeapidockercompose.repository.ResumeRepository;
import dev.mhzars.projects.mongo.resumeapidockercompose.validator.ResumeValidator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;

@Service
@AllArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository repo;
    private final ResumeValidator validator;
    private final CommonResumeValidator commonValidator;

    private static void removeChildRecords(ResumeRequest request) {
        request.setEducationList(new ArrayList<>());
        request.setExperienceList(new ArrayList<>());
        request.setSkillList(new ArrayList<>());
    }

    @Override
    public List<ResumeResponse> getAllResumes() throws JsonProcessingException {
        List<Resume> response = repo.findAll();

        return mapFromJsonList(response, ResumeResponse.class);
    }

    @Override
    public ResumeResponse getResumeById(String id) {
        Resume resume =
                repo.findById(validateObjectId(id))
                        .orElseThrow(
                                () ->
                                        new CustomNotFoundException(
                                                String.format(
                                                        "Resume with id %s was not found", id)));
        return COMMON_MAPPER.convertValue(resume, ResumeResponse.class);
    }

    private void removeChildRecordsAndSaveResume(ResumeRequest request, String id) {
        removeChildRecords(request);
        saveResume(request, id);
    }

    private void validateAndSaveResume(ResumeRequest request) {
        commonValidator.validate(request);
        validator.validate(request);
        Resume resume = COMMON_MAPPER.convertValue(request, Resume.class);
        checkChildTables(resume);
        if (resume.getCreationDate() == null) resume.setCreationDate(LocalDateTime.now());
        repo.save(resume);
        request.setId(String.valueOf(resume.getId()));
    }

    private void checkChildTables(Resume resume) {
        resume.getEducationList()
                .forEach(
                        r -> {
                            if (r.getId() == null) r.setId(generateUniqueId());
                        });
        resume.getExperienceList()
                .forEach(
                        r -> {
                            if (r.getId() == null) r.setId(generateUniqueId());
                        });
        resume.getSkillList()
                .forEach(
                        r -> {
                            if (r.getId() == null) r.setId(generateUniqueId());
                        });
    }

    @Override
    public ResumeIdResponse saveResume(ResumeRequest request) {
        return saveResume(request, null);
    }

    @Override
    public ResumeIdResponse saveResume(ResumeRequest request, String id) {
        ObjectId resumeId;
        if (id == null) {
            resumeId =
                    (request.getId() == null || request.getId().isEmpty())
                            ? generateUniqueId()
                            : validateObjectId(request.getId());
        } else {
            resumeId = COMMON_MAPPER.convertValue(getResumeById(id), Resume.class).getId();
        }

        request.setId(String.valueOf(resumeId));

        validateAndSaveResume(request);

        return new ResumeIdResponse(request.getId());
    }

    @Override
    public ResumeIdResponse deleteResumeById(String id) {
        Resume resume =
                repo.findById(validateObjectId(id))
                        .orElseThrow(
                                () ->
                                        new CustomNotFoundException(
                                                String.format(
                                                        "No Record was found for resumeId %s",
                                                        id)));
        ResumeRequest request = COMMON_MAPPER.convertValue(resume, ResumeRequest.class);
        removeChildRecordsAndSaveResume(request, id);
        repo.deleteById(validateObjectId(id));

        return new ResumeIdResponse(id);
    }

    @Override
    public ResumeResponse getResumeByFirstName(String firstName) {
        Resume resume =
                repo.findFirstByFirstName(firstName)
                        .orElseThrow(
                                () ->
                                        new CustomNotFoundException(
                                                String.format(
                                                        "Resume with firstName %s was not found",
                                                        firstName)));
        return COMMON_MAPPER.convertValue(resume, ResumeResponse.class);
    }
}
