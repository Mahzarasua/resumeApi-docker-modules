package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import java.lang.annotation.Annotation;
import java.util.List;
import uk.co.jemos.podam.common.AttributeStrategy;

public class PhoneStrategy implements AttributeStrategy<String> {

    @Override
    public String getValue(Class<?> aClass, List<Annotation> list) {
        return "+521234567891";
    }
}
