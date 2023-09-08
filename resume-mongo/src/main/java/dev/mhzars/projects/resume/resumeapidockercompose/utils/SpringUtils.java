package dev.mhzars.projects.resume.resumeapidockercompose.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.mhzars.projects.resume.resumeapidockercompose.exception.CustomBadRequestException;
import dev.mhzars.projects.resume.resumeapidockercompose.exception.ExceptionBody;
import org.bson.types.ObjectId;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

public class SpringUtils {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .registerModule(new Jdk8Module())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private SpringUtils() {
    }

    public static UUID getUuid(String resumeId) {
        return UUID.fromString(resumeId);
    }

    public static UUID getRandomId() {
        return UUID.randomUUID();
    }

    public static String mapToJson(Object obj) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(obj);
    }

    public static <T> T mapFromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(json, clazz);
    }

    public static <T> T mapFromJsonList(String json, TypeReference<T> clazz) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(json, clazz);
    }

    public static <T> void removeFromList(List<T> list, Predicate<T> predicate) {
        list.removeIf(predicate);
    }

    public static List<String> getExceptionMessageChain(Throwable throwable) {
        List<String> result = new ArrayList<>();
        while (throwable != null) {
            result.add(throwable.getMessage());
            throwable = throwable.getCause();
        }
        return result;
    }

    public static ObjectId generateUniqueObjectId() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int time = (int) timestamp.getTime() + new Random().nextInt(200);
        return new ObjectId(time, new Random().nextInt(100));
    }

    public static ObjectId validateObjectId(String id) {
        try {
            return new ObjectId(id);
        } catch (IllegalArgumentException e) {
            ExceptionBody.ErrorDetails errorDetails =
                    new ExceptionBody.ErrorDetails("id",
                            String.format("Value provided: %s cannot be converted to ObjectId", id));
            throw new CustomBadRequestException(Collections.singletonList(errorDetails), "Conversion Error");
        }
    }
}
