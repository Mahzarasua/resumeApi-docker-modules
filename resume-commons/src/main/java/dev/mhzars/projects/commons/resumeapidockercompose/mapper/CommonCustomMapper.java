package dev.mhzars.projects.commons.resumeapidockercompose.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import org.bson.types.ObjectId;

public class CommonCustomMapper {

    public static final ObjectMapper COMMON_MAPPER = generateCustomMapper();

    private CommonCustomMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static JsonMapper generateCustomMapper() {
        JsonMapper mapper =
                JsonMapper.builder()
                        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                        .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                        //                .serializationInclusion(JsonInclude.Include.NON_NULL)
                        .build();

        mapper.registerModule(new JavaTimeModule());

        // Register custom serializer for ObjectId
        SimpleModule customModule = new SimpleModule();
        customModule.addSerializer(
                ObjectId.class,
                new JsonSerializer<ObjectId>() {
                    @Override
                    public void serialize(
                            ObjectId objectId,
                            JsonGenerator jsonGenerator,
                            SerializerProvider serializerProvider)
                            throws IOException {
                        jsonGenerator.writeString(objectId.toString());
                    }
                });

        mapper.registerModule(customModule);

        return mapper;
    }
}
