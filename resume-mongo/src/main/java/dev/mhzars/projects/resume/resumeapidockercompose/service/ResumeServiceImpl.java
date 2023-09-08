package dev.mhzars.projects.resume.resumeapidockercompose.service;

import dev.mhzars.projects.resume.resumeapidockercompose.domain.resume.ResumeIdResponse;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.resume.ResumeRequest;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.resume.ResumeResponse;
import dev.mhzars.projects.resume.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.resume.resumeapidockercompose.mapper.CustomMapper;
import dev.mhzars.projects.resume.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.resume.resumeapidockercompose.repository.ResumeRepository;
import dev.mhzars.projects.resume.resumeapidockercompose.validator.ResumeValidator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static dev.mhzars.projects.resume.resumeapidockercompose.utils.SpringUtils.generateUniqueObjectId;
import static dev.mhzars.projects.resume.resumeapidockercompose.utils.SpringUtils.validateObjectId;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository repo;
    private final CustomMapper mapper;
    private final ResumeValidator validator;

    public ResumeServiceImpl(ResumeRepository repo, CustomMapper mapper, ResumeValidator validator) {
        this.repo = repo;
        this.mapper = mapper;
        this.validator = validator;
    }

    private static void removeChildRecords(ResumeRequest request) {
        request.setEducationList(new ArrayList<>());
        request.setExperienceList(new ArrayList<>());
        request.setSkillList(new ArrayList<>());
    }

    @Override
    public List<ResumeResponse> getAllResumes() {
        List<Resume> response = repo.findAll();
        return mapper.mapAsList(response, ResumeResponse.class);
    }

    @Override
    public ResumeResponse getResumeById(String id) {
        Resume resume = repo.findById(validateObjectId(id))
                .orElseThrow(() -> new CustomNotFoundException(String.format("Resume with id %s was not found", id)));
        return mapper.map(resume, ResumeResponse.class);
    }

    private void removeChildRecordsAndSaveResume(ResumeRequest request, String id) {
        removeChildRecords(request);

        saveResume(request, id);
    }

    private void validateAndSaveResume(ResumeRequest request) {
        validator.validate(request);
        Resume resume = mapper.map(request, Resume.class);
        checkChildTables(resume);
        if (resume.getCreationDate() == null)
            resume.setCreationDate(LocalDateTime.now());
        repo.save(resume);
    }

    private void checkChildTables(Resume resume) {
        resume.getEducationList().forEach(r -> {
            if (r.getId() == null) r.setId(generateUniqueObjectId());
        });
        resume.getExperienceList().forEach(r -> {
            if (r.getId() == null) r.setId(generateUniqueObjectId());
        });
        resume.getSkillList().forEach(r -> {
            if (r.getId() == null) r.setId(generateUniqueObjectId());
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
            resumeId = (request.getId() == null || request.getId().isEmpty()) ? generateUniqueObjectId() : validateObjectId(request.getId());
        } else {
            resumeId = mapper.map(getResumeById(id), Resume.class).getId();
        }

        request.setId(String.valueOf(resumeId));

        validateAndSaveResume(request);

        return new ResumeIdResponse(request.getId());
    }

    @Override
    public ResumeIdResponse deleteResumeById(String id) {
        Resume resume = repo.findById(validateObjectId(id))
                .orElseThrow(() -> new CustomNotFoundException(String.format("No Record was found for resumeId %s", id)));

        ResumeRequest request = mapper.map(resume, ResumeRequest.class);

        removeChildRecordsAndSaveResume(request, id);
        repo.deleteById(validateObjectId(id));

        return new ResumeIdResponse(id);
    }

    @Override
    public ResumeResponse getResumeByFirstName(String firstName) {
        Resume resume = repo.findFirstByFirstName(firstName)
                .orElseThrow(() -> new CustomNotFoundException(String.format("Resume with firstName %s was not found", firstName)));
        return mapper.map(resume, ResumeResponse.class);
    }
}
