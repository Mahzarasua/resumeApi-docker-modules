package dev.mhzars.projects.resume.resumeapidockercompose.validator;

public interface CustomValidator<E> {
    void validate(E request);
}
