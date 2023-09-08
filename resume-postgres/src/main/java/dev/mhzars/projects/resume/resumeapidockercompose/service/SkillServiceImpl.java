package dev.mhzars.projects.resume.resumeapidockercompose.service;

import dev.mhzars.projects.resume.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.skill.SkillDomain;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.skill.SkillRequest;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.skill.SkillResponse;
import dev.mhzars.projects.resume.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.resume.resumeapidockercompose.mapper.CustomMapper;
import dev.mhzars.projects.resume.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.resume.resumeapidockercompose.model.Skill;
import dev.mhzars.projects.resume.resumeapidockercompose.repository.ResumeRepository;
import dev.mhzars.projects.resume.resumeapidockercompose.utils.SpringResumeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static dev.mhzars.projects.resume.resumeapidockercompose.utils.SpringUtils.removeFromList;

@Service
public class SkillServiceImpl implements SkillService {
    public static final String EXCEPTION_MSG = "No Skill record was found for resumeId %s";
    private final ResumeRepository repo;
    private final CustomMapper mapper;
    private final SpringResumeRepo checkResume;

    public SkillServiceImpl(ResumeRepository resumeRepo, CustomMapper mapper, SpringResumeRepo checkResume) {
        this.repo = resumeRepo;
        this.mapper = mapper;
        this.checkResume = checkResume;
    }

    @Override
    public SkillResponse getListbyResumeId(UUID resumeId) {
        Resume resume = checkResume.checkResumeId(resumeId);
        List<Skill> list = resume.getSkillList();

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(EXCEPTION_MSG, resumeId));

        return new SkillResponse(mapper.mapAsList(list, SkillDomain.class));
    }

    @Override
    public SkillResponse saveList(SkillRequest request) {
        UUID resumeId = request.getSkillList().get(0).getResumeId();
        Resume resume = checkResume.checkResumeId(resumeId);

        request.getSkillList().forEach(e -> e.setResumeId(resume.getId()));
        for (Skill e : mapper.mapAsList(request.getSkillList(), Skill.class)) {
            if (!resume.getSkillList().contains(e)) {
                resume.getSkillList().add(e);
            }
        }

        repo.save(resume);

        return getListbyResumeId(resumeId);
    }

    @Override
    public GenericDeleteResponse deleteRecordsbyResumeId(UUID resumeId) {
        Resume resume = checkResume.checkResumeId(resumeId);

        List<Skill> list = resume.getSkillList();

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(EXCEPTION_MSG, resumeId));

        resume.getSkillList().clear();
        repo.save(resume);

        return new GenericDeleteResponse(resumeId);
    }

    @Override
    public GenericDeleteResponse deleteRecordbyId(UUID resumeId, UUID id) {
        Resume resume = checkResume.checkResumeId(resumeId);
        removeFromList(resume.getSkillList(), row -> row.getId() == id && resume.getId() == resumeId);
        repo.save(resume);
        return new GenericDeleteResponse(id, resumeId);
    }
}
