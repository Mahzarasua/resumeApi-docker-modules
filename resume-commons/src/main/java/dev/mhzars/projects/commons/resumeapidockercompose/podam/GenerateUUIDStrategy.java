package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.UUID;
import uk.co.jemos.podam.common.AttributeStrategy;

public class GenerateUUIDStrategy implements AttributeStrategy<String> {

    @Override
    public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        return UUID.randomUUID().toString();
    }
}
