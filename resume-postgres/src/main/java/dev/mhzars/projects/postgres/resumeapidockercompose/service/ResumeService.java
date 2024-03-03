package dev.mhzars.projects.postgres.resumeapidockercompose.service;

import dev.mhzars.projects.postgres.resumeapidockercompose.domain.resume.ResumeIdResponse;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.resume.ResumeRequest;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.resume.ResumeResponse;

import java.util.List;
import java.util.UUID;

public interface ResumeService {
    List<ResumeResponse> getAllResumes();

    ResumeResponse getResumeById(UUID id);

    ResumeIdResponse saveResume(ResumeRequest request);

    ResumeIdResponse saveResume(ResumeRequest request, UUID id);

    ResumeIdResponse deleteResumeById(UUID id);

    ResumeResponse getResumeByFirstName(String firstName);
}
