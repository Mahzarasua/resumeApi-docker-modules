package dev.mhzars.projects.postgres.resumeapidockercompose.service;

import dev.mhzars.projects.postgres.resumeapidockercompose.domain.resume.ResumeIdResponse;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.resume.ResumeRequest;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.resume.ResumeResponse;
import dev.mhzars.projects.postgres.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.postgres.resumeapidockercompose.mapper.CustomMapper;
import dev.mhzars.projects.postgres.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.postgres.resumeapidockercompose.repository.ResumeRepository;
import dev.mhzars.projects.postgres.resumeapidockercompose.validator.ResumeValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils.getRandomId;

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
    public ResumeResponse getResumeById(UUID id) {
        Resume resume = repo.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(String.format("Resume with id %s was not found", id)));
        return mapper.map(resume, ResumeResponse.class);
    }

    private void removeChildRecordsAndSaveResume(ResumeRequest request, UUID id) {
        removeChildRecords(request);

        saveResume(request, id);
    }

    private void validateAndSaveResume(ResumeRequest request) {
        validator.validate(request);
        Resume resume = mapper.map(request, Resume.class);
        repo.save(resume);
        request.setId(resume.getId());
    }

    @Override
    public ResumeIdResponse saveResume(ResumeRequest request) {
        return saveResume(request, null);
    }

    @Override
    public ResumeIdResponse saveResume(ResumeRequest request, UUID id) {
        UUID resumeId = null;
        if (id == null) {
            resumeId = (request.getId() == null) ? getRandomId() : request.getId();
        } else {
            resumeId = mapper.map(getResumeById(id), Resume.class).getId();
        }

        request.setId(resumeId);

        validateAndSaveResume(request);

        return new ResumeIdResponse(request.getId());
    }

    @Override
    public ResumeIdResponse deleteResumeById(UUID id) {
        Resume resume = repo.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(String.format("No Record was found for resumeId %s", id)));

        ResumeRequest request = mapper.map(resume, ResumeRequest.class);

        removeChildRecordsAndSaveResume(request, id);
        repo.deleteById(id);

        return new ResumeIdResponse(id);
    }

    @Override
    public ResumeResponse getResumeByFirstName(String firstName) {
        Resume resume = repo.findFirstByFirstName(firstName)
                .orElseThrow(() -> new CustomNotFoundException(String.format("Resume with firstName %s was not found", firstName)));
        return mapper.map(resume, ResumeResponse.class);
    }
}
