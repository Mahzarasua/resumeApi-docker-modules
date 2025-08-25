package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import java.lang.annotation.Annotation;
import java.util.List;
import org.bson.types.ObjectId;
import uk.co.jemos.podam.common.AttributeStrategy;

public class GenerateObjectIdStrategy implements AttributeStrategy<String> {

    @Override
    public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        return new ObjectId().toString();
    }
}
