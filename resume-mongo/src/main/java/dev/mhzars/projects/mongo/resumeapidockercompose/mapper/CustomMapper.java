package dev.mhzars.projects.mongo.resumeapidockercompose.mapper;

import dev.mhzars.projects.mongo.resumeapidockercompose.config.MyUserDetails;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.auth.UserResponse;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.education.EducationDomain;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.experience.ExperienceDomain;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.skill.SkillDomain;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.AuthRole;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.AuthUser;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Education;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Experience;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Skill;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Primary
public class CustomMapper extends ConfigurableMapper {
    public static final String AUTH_ROLE = "role";
    public static final String DOMAIN_ROLE_NAME = "roleName";
    public static final String PARENT_RESUME_ID = "resume.id";
    public static final String DOMAIN_RESUME_ID = "resumeId";

    @Override
    protected void configure(MapperFactory factory) {
        factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));
        factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDateTime.class));
        factory.getConverterFactory().registerConverter(new BidirectionalStringAndUUIDConverter());

        factory.classMap(Experience.class, ExperienceDomain.class)
//                .field(PARENT_RESUME_ID, DOMAIN_RESUME_ID)
                .byDefault().mapNulls(false)
                .register();

        factory.classMap(Education.class, EducationDomain.class)
//                .field(PARENT_RESUME_ID, DOMAIN_RESUME_ID)
                .byDefault().mapNulls(false)
                .register();

        factory.classMap(Skill.class, SkillDomain.class)
//                .field(PARENT_RESUME_ID, DOMAIN_RESUME_ID)
                .byDefault().mapNulls(false)
                .register();

        factory.classMap(AuthUser.class, UserResponse.class)
                .byDefault().mapNulls(false)
                .register();

        factory.classMap(AuthRole.class, UserResponse.Role.class)
                .field(AUTH_ROLE, DOMAIN_ROLE_NAME)
                .byDefault().mapNulls(false)
                .register();

        factory.classMap(AuthUser.class, MyUserDetails.class)
                .byDefault().mapNulls(false)
                .register();
    }
}