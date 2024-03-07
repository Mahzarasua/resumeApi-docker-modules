package dev.mhzars.projects.postgres.resumeapidockercompose.podam;

import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.util.List;

public class LocalDateFutureStrategy implements AttributeStrategy<LocalDate> {
    @Override
    public LocalDate getValue(Class<?> aClass, List<Annotation> list) {
        return LocalDate.now();
    }
}
