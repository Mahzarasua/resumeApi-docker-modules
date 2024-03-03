package dev.mhzars.projects.postgres.resumeapidockercompose.validator;

public interface CustomValidator<E> {
    void validate(E request);
}
