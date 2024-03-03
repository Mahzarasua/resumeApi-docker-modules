package dev.mhzars.projects.mongo.resumeapidockercompose.validator;

public interface CustomValidator<E> {
    void validate(E request);
}
