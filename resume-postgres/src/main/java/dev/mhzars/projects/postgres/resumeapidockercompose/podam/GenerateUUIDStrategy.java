package dev.mhzars.projects.postgres.resumeapidockercompose.podam;

import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;
import java.util.List;

import static dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils.generateUniqueObjectId;


public class GenerateUUIDStrategy implements AttributeStrategy<String> {

    @Override
    public String getValue(Class<?> aClass, List<Annotation> list) {
        return generateUniqueObjectId().toString();
    }
}
