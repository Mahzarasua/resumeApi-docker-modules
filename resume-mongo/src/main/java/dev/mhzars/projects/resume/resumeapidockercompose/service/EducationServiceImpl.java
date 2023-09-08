package dev.mhzars.projects.resume.resumeapidockercompose.service;

import dev.mhzars.projects.resume.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.education.EducationDomain;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.education.EducationRequest;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.education.EducationResponse;
import dev.mhzars.projects.resume.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.resume.resumeapidockercompose.mapper.CustomMapper;
import dev.mhzars.projects.resume.resumeapidockercompose.model.Education;
import dev.mhzars.projects.resume.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.resume.resumeapidockercompose.repository.ResumeRepository;
import dev.mhzars.projects.resume.resumeapidockercompose.utils.SpringResumeRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static dev.mhzars.projects.resume.resumeapidockercompose.utils.SpringUtils.generateUniqueObjectId;
import static dev.mhzars.projects.resume.resumeapidockercompose.utils.SpringUtils.removeFromList;
import static dev.mhzars.projects.resume.resumeapidockercompose.utils.SpringUtils.validateObjectId;

@Service
public class EducationServiceImpl implements EducationService {

    public static final String EXCEPTION_MSG = "No Education record was found for resumeId %s";
    private final ResumeRepository repo;
    private final CustomMapper mapper;
    private final SpringResumeRepo checkResume;

    public EducationServiceImpl(ResumeRepository resumeRepo, CustomMapper mapper, SpringResumeRepo checkResume) {
        this.repo = resumeRepo;
        this.mapper = mapper;
        this.checkResume = checkResume;
    }

    @Override
    public EducationResponse getListbyResumeId(String resumeId) {
        Resume resume = checkResume.checkResumeId(resumeId);
        List<Education> list = resume.getEducationList();

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(EXCEPTION_MSG, resumeId));

        return new EducationResponse(mapper.mapAsList(list, EducationDomain.class));
    }

    @Override
    public EducationResponse saveList(EducationRequest request) {
        String resumeId = request.getEducationList().get(0).getResumeId();
        Resume resume = checkResume.checkResumeId(resumeId);

        request.getEducationList().forEach(e -> e.setResumeId(String.valueOf(resume.getId())));
        for (Education e : mapper.mapAsList(request.getEducationList(), Education.class)) {
            if (e.getId() == null) e.setId(generateUniqueObjectId());
            if (e.getCreationDate() == null) e.setCreationDate(LocalDateTime.now());
            if (!resume.getEducationList().contains(e)) {
                resume.getEducationList().add(e);
            }
        }

        repo.save(resume);

        return getListbyResumeId(resumeId);
    }

    @Override
    public GenericDeleteResponse deleteRecordsbyResumeId(String resumeId) {
        Resume resume = checkResume.checkResumeId(resumeId);

        List<Education> list = resume.getEducationList();

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(EXCEPTION_MSG, resumeId));

        resume.getEducationList().clear();
        repo.save(resume);

        return new GenericDeleteResponse(resumeId);
    }

    @Override
    public GenericDeleteResponse deleteRecordbyId(String resumeId, String id) {
        Resume resume = checkResume.checkResumeId(resumeId);
        checkResumeEducationList(resume, id);
        removeFromList(resume.getEducationList(), row -> Objects.equals(row.getId(), validateObjectId(id)) && Objects.equals(resume.getId(), validateObjectId(resumeId)));
        repo.save(resume);
        return new GenericDeleteResponse(id, resumeId);
    }

    private void checkResumeEducationList(Resume resume, String id) {
        boolean recNotFound = true;
        for (Education e : resume.getEducationList()) {
            if (Objects.equals(e.getId(), validateObjectId(id))) {
                recNotFound = false;
            }
        }
        if (recNotFound)
            throw new CustomNotFoundException(String.format("Education with id %s was not found", id));
    }
}
