package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.util.List;
import uk.co.jemos.podam.common.AttributeStrategy;

public class LocalDatePastStrategy implements AttributeStrategy<LocalDate> {
    @Override
    public LocalDate getValue(Class<?> aClass, List<Annotation> list) {
        return LocalDate.of(2023, 12, 25);
    }
}
