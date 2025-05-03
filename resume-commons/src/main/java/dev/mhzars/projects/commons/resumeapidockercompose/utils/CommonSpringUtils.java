package dev.mhzars.projects.commons.resumeapidockercompose.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.io.InputStream;
import java.security.DrbgParameters;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

import static dev.mhzars.projects.commons.resumeapidockercompose.mapper.CommonCustomMapper.COMMON_MAPPER;
import static java.security.DrbgParameters.Capability.RESEED_ONLY;

public class CommonSpringUtils {
    private static Random random;

    static {
        try {
            random =
                    SecureRandom.getInstance(
                            "DRBG", DrbgParameters.instantiation(128, RESEED_ONLY, null));
        } catch (NoSuchAlgorithmException e) {
            random = new SecureRandom();
        }
    }

    protected CommonSpringUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static UUID getUuid(String resumeId) {
        return UUID.fromString(resumeId);
    }

    public static UUID getRandomId() {
        return UUID.randomUUID();
    }

    public static String mapToJson(Object obj) throws JsonProcessingException {
        return COMMON_MAPPER.writeValueAsString(obj);
    }

    public static <T> T mapFromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return COMMON_MAPPER.readValue(json, clazz);
    }

    public static <T, R> List<T> mapFromJsonList(List<R> previousList, Class<T> elementClass)
            throws JsonProcessingException {
        String json = COMMON_MAPPER.writeValueAsString(previousList);
        // Dynamically create a TypeReference for List<T>
        TypeReference<List<T>> typeRef =
                new TypeReference<>() {
                    @Override
                    public java.lang.reflect.Type getType() {
                        return COMMON_MAPPER
                                .getTypeFactory()
                                .constructCollectionType(List.class, elementClass);
                    }
                };
        return COMMON_MAPPER.readValue(json, typeRef);
    }

    public static <T> T mapFromJsonList(String json, TypeReference<T> clazz)
            throws JsonProcessingException {
        return COMMON_MAPPER.readValue(json, clazz);
    }

    public static <T> List<T> jsonArrayToList(String json, Class<T> elementClass)
            throws IOException {
        CollectionType listType =
                COMMON_MAPPER
                        .getTypeFactory()
                        .constructCollectionType(ArrayList.class, elementClass);
        return COMMON_MAPPER.readValue(json, listType);
    }

    public static <T, X> List<T> readFile(
            String filename, Class<T> entityClass, Class<X> objectClass) {
        String json;
        List<T> entities;
        try (InputStream in = objectClass.getClassLoader().getResourceAsStream(filename)) {
            JsonNode jsonNode = COMMON_MAPPER.readValue(in, JsonNode.class);
            json = COMMON_MAPPER.writeValueAsString(jsonNode);
            entities =
                    jsonNode.isArray()
                            ? jsonArrayToList(json, entityClass)
                            : Collections.singletonList(COMMON_MAPPER.readValue(json, entityClass));
            return entities;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
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

    public static <T> T generateUniqueId() {
        boolean isMongoDB =
                Boolean.parseBoolean(
                        System.getProperty("db.mongo", "true")); // Default to "postgres"
        if (isMongoDB) {
            return (T) generateUniqueObjectId();
        }
        return (T) getRandomId();
    }

    public static ObjectId generateUniqueObjectId() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int time = (int) timestamp.getTime() + random.nextInt(200);
        return new ObjectId(time, random.nextInt(100));
    }
}
