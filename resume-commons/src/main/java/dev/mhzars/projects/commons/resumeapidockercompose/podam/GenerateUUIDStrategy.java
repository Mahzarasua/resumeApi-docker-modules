package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import static dev.mhzars.projects.commons.resumeapidockercompose.utils.CommonSpringUtils.generateUniqueObjectId;

import java.lang.annotation.Annotation;
import java.util.List;
import uk.co.jemos.podam.common.AttributeStrategy;

public class GenerateUUIDStrategy implements AttributeStrategy<String> {

    @Override
    public String getValue(Class<?> aClass, List<Annotation> list) {
        return generateUniqueObjectId();
    }
}
