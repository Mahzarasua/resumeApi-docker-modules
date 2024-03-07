package dev.mhzars.projects.postgres.resumeapidockercompose.domain.education;

import lombok.Getter;

@Getter
public enum EducationDegreeEnum {
    BACHELOR("Bachelor"),
    MASTER("Master"),
    PH("Ph");

    final String degree;

    EducationDegreeEnum(String degree) {
        this.degree = degree;
    }
}
