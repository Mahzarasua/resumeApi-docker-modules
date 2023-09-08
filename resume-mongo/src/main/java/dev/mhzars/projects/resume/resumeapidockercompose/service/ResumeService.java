package dev.mhzars.projects.resume.resumeapidockercompose.service;

import dev.mhzars.projects.resume.resumeapidockercompose.domain.resume.ResumeIdResponse;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.resume.ResumeRequest;
import dev.mhzars.projects.resume.resumeapidockercompose.domain.resume.ResumeResponse;

import java.util.List;

public interface ResumeService {
    List<ResumeResponse> getAllResumes();

    ResumeResponse getResumeById(String id);

    ResumeIdResponse saveResume(ResumeRequest request);

    ResumeIdResponse saveResume(ResumeRequest request, String id);

    ResumeIdResponse deleteResumeById(String id);

    ResumeResponse getResumeByFirstName(String firstName);
}
