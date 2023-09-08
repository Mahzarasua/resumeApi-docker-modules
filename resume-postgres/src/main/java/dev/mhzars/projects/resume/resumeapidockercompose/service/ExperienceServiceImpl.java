package dev.mhzars.projects.resume.resumeapidockercompose.service;

import dev.mhzars.projects.resume.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.experience.ExperienceDomain;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.experience.ExperienceRequest;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.experience.ExperienceResponse;
import dev.mhzars.projects.resume.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.resume.resumeapidockercompose.mapper.CustomMapper;
import dev.mhzars.projects.resume.resumeapidockercompose.model.Experience;
import dev.mhzars.projects.resume.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.resume.resumeapidockercompose.repository.ResumeRepository;
import dev.mhzars.projects.resume.resumeapidockercompose.utils.SpringResumeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static dev.mhzars.projects.resume.resumeapidockercompose.utils.SpringUtils.removeFromList;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    public static final String EXCEPTION_MSG = "No Experience record was found for resumeId %s";
    private final ResumeRepository repo;
    private final CustomMapper mapper;
    private final SpringResumeRepo checkResume;

    public ExperienceServiceImpl(ResumeRepository resumeRepo, CustomMapper mapper, SpringResumeRepo checkResume) {
        this.repo = resumeRepo;
        this.mapper = mapper;
        this.checkResume = checkResume;
    }

    @Override
    public ExperienceResponse getListbyResumeId(UUID resumeId) {
        Resume resume = checkResume.checkResumeId(resumeId);
        List<Experience> list = resume.getExperienceList();

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(EXCEPTION_MSG, resumeId));

        return new ExperienceResponse(mapper.mapAsList(list, ExperienceDomain.class));
    }

    @Override
    public ExperienceResponse saveList(ExperienceRequest request) {
        UUID resumeId = request.getExperienceList().get(0).getResumeId();
        Resume resume = checkResume.checkResumeId(resumeId);

        request.getExperienceList().forEach(e -> e.setResumeId(resume.getId()));
        for (Experience e : mapper.mapAsList(request.getExperienceList(), Experience.class)) {
            if (!resume.getExperienceList().contains(e)) {
                resume.getExperienceList().add(e);
            }
        }

        repo.save(resume);

        return getListbyResumeId(resumeId);
    }

    @Override
    public GenericDeleteResponse deleteRecordsbyResumeId(UUID resumeId) {
        Resume resume = checkResume.checkResumeId(resumeId);

        List<Experience> list = resume.getExperienceList();

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(EXCEPTION_MSG, resumeId));

        resume.getExperienceList().clear();
        repo.save(resume);

        return new GenericDeleteResponse(resumeId);
    }

    @Override
    public GenericDeleteResponse deleteRecordbyId(UUID resumeId, UUID id) {
        Resume resume = checkResume.checkResumeId(resumeId);
        removeFromList(resume.getExperienceList(), row -> row.getId() == id && resume.getId() == resumeId);
        repo.save(resume);
        return new GenericDeleteResponse(id, resumeId);
    }
}
