package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import java.lang.annotation.Annotation;
import java.util.List;
import uk.co.jemos.podam.common.AttributeStrategy;

public class EmailStrategy implements AttributeStrategy<String> {

    @Override
    public String getValue(Class<?> aClass, List<Annotation> list) {
        return "aaa.bbb@ccc.ddd";
    }
}
