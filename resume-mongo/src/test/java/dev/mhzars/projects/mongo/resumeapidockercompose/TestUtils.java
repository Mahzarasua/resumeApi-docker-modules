package dev.mhzars.projects.mongo.resumeapidockercompose;

import dev.mhzars.projects.mongo.resumeapidockercompose.domain.resume.ResumeRequest;
import dev.mhzars.projects.mongo.resumeapidockercompose.mapper.CustomMapper;
import dev.mhzars.projects.mongo.resumeapidockercompose.podam.EmailStrategy;
import dev.mhzars.projects.mongo.resumeapidockercompose.podam.LocalDateFutureStrategy;
import dev.mhzars.projects.mongo.resumeapidockercompose.podam.LocalDatePastStrategy;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Past;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategy;

import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.generateUniqueObjectId;

public class TestUtils {
    public static final CustomMapper MAPPER = new CustomMapper();
    public static final String RESUME_ID = "resumeId";
    public static final String TEST_EMAIL = "testemail@testemail.com";
    public static final String TEST_PHONE = "+521234567891";

    public static <T> T manufacturedPojo(Class<T> clazz) {
        PodamFactory factory = new PodamFactoryImpl();
        return factory.manufacturePojo(clazz);
    }

    public static <T> T manufacturedCustomPojo(Class<T> clazz) {
        PodamFactory factory = new PodamFactoryImpl();
        EmailStrategy emailStrategy = new EmailStrategy();
        LocalDatePastStrategy pastStrategy = new LocalDatePastStrategy();
        LocalDateFutureStrategy futureStrategy = new LocalDateFutureStrategy();
        ((RandomDataProviderStrategy) factory.getStrategy()).addOrReplaceAttributeStrategy(Email.class, emailStrategy);
        ((RandomDataProviderStrategy) factory.getStrategy()).addOrReplaceAttributeStrategy(Past.class, pastStrategy);
        ((RandomDataProviderStrategy) factory.getStrategy()).addOrReplaceAttributeStrategy(FutureOrPresent.class, futureStrategy);
        ((RandomDataProviderStrategy) factory.getStrategy()).addOrReplaceAttributeStrategy(javax.validation.constraints.Past.class, pastStrategy);
        ((RandomDataProviderStrategy) factory.getStrategy()).addOrReplaceAttributeStrategy(javax.validation.constraints.FutureOrPresent.class, futureStrategy);

        return factory.manufacturePojo(clazz);
    }


    public static void setChildTables(ResumeRequest request) {
        request.getEducationList().forEach(r -> {
            r.setId(generateUniqueObjectId().toString());
        });
        request.getExperienceList().forEach(r -> {
            r.setId(generateUniqueObjectId().toString());
        });
        request.getSkillList().forEach(r -> {
            r.setId(generateUniqueObjectId().toString());
        });
    }


}
