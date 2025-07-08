package dev.mhzars.projects.postgres.resumeapidockercompose.service;

import static dev.mhzars.projects.commons.resumeapidockercompose.mapper.CommonCustomMapper.COMMON_MAPPER;
import static dev.mhzars.projects.commons.resumeapidockercompose.utils.CommonSpringUtils.generateUniqueId;
import static dev.mhzars.projects.commons.resumeapidockercompose.utils.CommonSpringUtils.mapFromJsonList;
import static dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils.validateObjectId;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.CommonResumeRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.ResumeIdResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.ResumeResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.commons.resumeapidockercompose.service.ResumeService;
import dev.mhzars.projects.commons.resumeapidockercompose.validator.CommonResumeValidator;
import dev.mhzars.projects.commons.resumeapidockercompose.validator.ResumeValidator;
import dev.mhzars.projects.postgres.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.postgres.resumeapidockercompose.repository.ResumeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository repo;
    private final ResumeValidator validator;
    private final CommonResumeValidator commonValidator;

    private static void removeChildRecords(CommonResumeRequest request) {
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

    private void removeChildRecordsAndSaveResume(CommonResumeRequest request, String id) {
        removeChildRecords(request);
        saveResume(request, id);
    }

    private void validateAndSaveResume(CommonResumeRequest request) {
        commonValidator.validate(request);
        validator.validate(request);
        Resume resume = COMMON_MAPPER.convertValue(request, Resume.class);
        repo.save(resume);
        request.setId(String.valueOf(resume.getId()));
    }

    @Override
    public ResumeIdResponse saveResume(CommonResumeRequest request) {
        return saveResume(request, null);
    }

    @Override
    public ResumeIdResponse saveResume(CommonResumeRequest request, String id) {
        UUID resumeId;
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
        CommonResumeRequest request = COMMON_MAPPER.convertValue(resume, CommonResumeRequest.class);
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
