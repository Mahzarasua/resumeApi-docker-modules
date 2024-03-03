package dev.mhzars.projects.mongo.resumeapidockercompose.validator;

import dev.mhzars.projects.mongo.resumeapidockercompose.domain.education.EducationDomain;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.experience.ExperienceDomain;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.resume.ResumeRequest;
import dev.mhzars.projects.mongo.resumeapidockercompose.exception.CustomBadRequestException;
import dev.mhzars.projects.mongo.resumeapidockercompose.exception.ExceptionBody;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeValidator implements CustomValidator<ResumeRequest> {
    @Override
    public void validate(ResumeRequest request) {
        List<ExceptionBody.ErrorDetails> errorDetails = new ArrayList<>();
        validateRequiredField(request, errorDetails);

        if (!errorDetails.isEmpty())
            throw new CustomBadRequestException(errorDetails, CustomValidationUtils.CUSTOM_ERROR_MSG);
    }

    private void validateRequiredField(ResumeRequest request, List<ExceptionBody.ErrorDetails> errorDetails) {
        if (!CustomValidationUtils.isValidString(request.getFirstName()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "First name"));
        if (!CustomValidationUtils.isValidString(request.getLastName()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Second name"));
        if (!CustomValidationUtils.isValidString(request.getEmail()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Email"));
        if (!CustomValidationUtils.isValidString(request.getPhone()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Phone number"));
        if (!CustomValidationUtils.isValidString(request.getCity()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "City"));
        if (!CustomValidationUtils.isValidString(request.getCountry()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Country"));
        if (!CustomValidationUtils.isValidEmail(request.getEmail()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.INVALID_FORMAT, "Email"));
        if (!CustomValidationUtils.isValidPhoneNumber(request.getPhone()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.INVALID_FORMAT, "Phone number"));

        validateSkills(request, errorDetails);
        validateWorkExperience(request, errorDetails);
        validateEducation(request, errorDetails);
    }

    private void validateSkills(ResumeRequest resumeRequest, List<ExceptionBody.ErrorDetails> errorDetails) {
        // If no resumeRequest were added, there are no validations to do here.
        if (resumeRequest.getSkillList() != null && !resumeRequest.getSkillList().isEmpty()) {
            resumeRequest.getSkillList().forEach(e -> {
                if (!CustomValidationUtils.isValidString(e.getName()))
                    errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Skill name"));
                if (e.getPercentage() <= 0 || e.getPercentage() > 100)
                    errorDetails.add(newErrorDetail(CustomValidationUtils.INVALID_FORMAT, "Percentage for skill " + e.getName()));
            });
        }
    }

    private void validateWorkExperience(ResumeRequest resumeRequest, List<ExceptionBody.ErrorDetails> errorDetails) {
        // If no work experiences were added, there are no validations to do here.
        if (resumeRequest.getExperienceList() != null && !resumeRequest.getExperienceList().isEmpty()) {
            resumeRequest.getExperienceList().forEach(e -> experienceValidator(errorDetails, e));
        }
    }

    private void experienceValidator(List<ExceptionBody.ErrorDetails> errorDetails, ExperienceDomain e) {
        if (!CustomValidationUtils.isValidString(e.getTitle()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Work title"));
        if (!CustomValidationUtils.isValidString(e.getCompany()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Company name for work " + e.getTitle()));
        if (e.getStartDate() == null)
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Beginning date for work " + e.getTitle() + " at " + e.getCompany()));
        if (!e.isCurrentJob()) {
            if (e.getEndDate() == null)
                errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Ending date for work " + e.getTitle() + " at " + e.getCompany()));
                // Verify the ending date is greater than the beginning date.
            else if (!e.getEndDate().isAfter(e.getStartDate()))
                errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Ending date for work " + e.getTitle() + " must be greater than beginning date"));
        }
    }

    private void educationValidator(List<ExceptionBody.ErrorDetails> errorDetails, EducationDomain e) {
        if (!CustomValidationUtils.isValidString(e.getName()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "School name"));
        if (!CustomValidationUtils.isValidString(e.getCareer()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Career name at " + e.getName()));
        if (!CustomValidationUtils.isValidString(e.getDegree()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Degree of your career at " + e.getName()));
        if (e.getDegree() != null && !e.getDegree().equals("Bachelor") && !e.getDegree().equals("Master") && !e.getDegree().equals("Ph"))
            errorDetails.add(newErrorDetail(CustomValidationUtils.INVALID_FORMAT, "Degree of you career at " + e.getName() + " should be either 'Bachelor', 'Master', or 'Ph'"));
        if (e.getStartDate() == null)
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Beginning date of your career at " + e.getName()));
        if (e.getEndDate() == null)
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "(Actual or expected) Ending date for your " + "career at " + e.getName()));
        if (e.getStartDate() != null && e.getEndDate() != null && !e.getEndDate().isAfter(e.getStartDate()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.INVALID_FORMAT, "(Actual or expected) Ending date for your career at " + e.getName() + " must be greater than beginning date"));
    }

    private void validateEducation(ResumeRequest resumeRequest, List<ExceptionBody.ErrorDetails> errorDetails) {
        // If no education is added, there are no validations to do here.
        if (resumeRequest.getEducationList() != null && !resumeRequest.getEducationList().isEmpty()) {
            resumeRequest.getEducationList().forEach(e -> educationValidator(errorDetails, e));
        }
    }

    private ExceptionBody.ErrorDetails newErrorDetail(String errorMessage, String fieldName) {
        ExceptionBody.ErrorDetails error = new ExceptionBody.ErrorDetails();
        error.setErrorMessage(errorMessage);
        error.setFieldName(fieldName);
        return error;
    }
}
