package dev.mhzars.projects.commons.resumeapidockercompose.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.CommonResumeRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.ResumeIdResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.ResumeResponse;
import java.util.List;

public interface ResumeService {
    List<ResumeResponse> getAllResumes() throws JsonProcessingException;

    ResumeResponse getResumeById(String id);

    ResumeIdResponse saveResume(CommonResumeRequest request);

    ResumeIdResponse saveResume(CommonResumeRequest request, String id);

    ResumeIdResponse deleteResumeById(String id);

    ResumeResponse getResumeByFirstName(String firstName);
}
