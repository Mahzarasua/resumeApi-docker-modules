package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.util.List;
import uk.co.jemos.podam.common.AttributeStrategy;

public class LocalDateFutureStrategy implements AttributeStrategy<LocalDate> {
    @Override
    public LocalDate getValue(Class<?> aClass, List<Annotation> list) {
        return LocalDate.now();
    }
}
