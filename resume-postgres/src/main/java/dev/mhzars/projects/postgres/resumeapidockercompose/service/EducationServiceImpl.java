package dev.mhzars.projects.postgres.resumeapidockercompose.service;

import dev.mhzars.projects.postgres.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.education.EducationDomain;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.education.EducationRequest;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.education.EducationResponse;
import dev.mhzars.projects.postgres.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.postgres.resumeapidockercompose.mapper.CustomMapper;
import dev.mhzars.projects.postgres.resumeapidockercompose.model.Education;
import dev.mhzars.projects.postgres.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.postgres.resumeapidockercompose.repository.ResumeRepository;
import dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringResumeRepo;
import dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public EducationResponse getListbyResumeId(UUID resumeId) {
        Resume resume = checkResume.checkResumeId(resumeId);
        List<Education> list = resume.getEducationList();

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(EXCEPTION_MSG, resumeId));

        return new EducationResponse(mapper.mapAsList(list, EducationDomain.class));
    }

    @Override
    public EducationResponse saveList(EducationRequest request) {
        UUID resumeId = request.getEducationList().get(0).getResumeId();
        Resume resume = checkResume.checkResumeId(resumeId);

        request.getEducationList().forEach(e -> e.setResumeId(resume.getId()));
        for (Education e : mapper.mapAsList(request.getEducationList(), Education.class)) {
            if (!resume.getEducationList().contains(e)) {
                resume.getEducationList().add(e);
            }
        }

        repo.save(resume);

        return getListbyResumeId(resumeId);
    }

    @Override
    public GenericDeleteResponse deleteRecordsbyResumeId(UUID resumeId) {
        Resume resume = checkResume.checkResumeId(resumeId);

        List<Education> list = resume.getEducationList();

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(EXCEPTION_MSG, resumeId));

        resume.getEducationList().clear();
        repo.save(resume);

        return new GenericDeleteResponse(resumeId);
    }

    @Override
    public GenericDeleteResponse deleteRecordbyId(UUID resumeId, UUID id) {
        Resume resume = checkResume.checkResumeId(resumeId);
        SpringUtils.removeFromList(resume.getEducationList(), row -> row.getId() == id && resume.getId() == resumeId);
        repo.save(resume);
        return new GenericDeleteResponse(id, resumeId);
    }
}
